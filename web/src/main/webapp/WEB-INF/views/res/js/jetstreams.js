/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
var Jetstreams = (function() {
    var SCALE = .0000008;
    
    var container, stats;
    
    var camera, scene, renderer;
    
    var globe, wind;
    
    function createContainer() {

        //create and add container
        container = document.createElement('div');
        document.body.appendChild(container);

        camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 1, 15);
        camera.position.z = 10;

        scene = new THREE.Scene();

        // Lights
        scene.add(new THREE.AmbientLight(0xffffff));

        addShadowedLight(5, 5, 5, 0xffffff, 1.5);

        // renderer
        renderer = new THREE.WebGLRenderer({antialias: true});
        renderer.setSize(window.innerWidth, window.innerHeight);

        renderer.gammaInput = true;
        renderer.gammaOutput = true;

        renderer.shadowMapEnabled = true;
        renderer.shadowMapCullFace = THREE.CullFaceBack;

        container.appendChild(renderer.domElement);

        addStats();

        window.addEventListener('resize', onWindowResize, false);
    }
    
    function loadAll(type, callback){
        loadGlobe(type, callback);
        loadWind();
    }
    
    function loadGlobe(type, callback){
        
        var loadListener = function(event) {
            var geometry = event.content;
            var material = new THREE.MeshLambertMaterial({ambient: 0x555555, color: 0xAAAAFF});
            globe = new THREE.Mesh(geometry, material);
            globe.rotation.set(-Math.PI / 2, 0, 0);
            //change scale if you choose a mesh with a different size
            globe.scale.set(SCALE, SCALE, SCALE);

            globe.castShadow = true;
            globe.receiveShadow = true;
            scene.add(globe);

            console.log("globe loaded");
            if (typeof callback === "function") {
                callback();
            }
        };
        loadMesh('globe/'+ type, loadListener);
    }
    
    function loadWind() {
        
        var loadListener = function(event) {
            var geometry = event.content;
            var material = new THREE.MeshLambertMaterial({ambient: 0x777777, color: 0xFFAAAA});
            wind = new THREE.Mesh(geometry, material);
            wind.rotation.set(-Math.PI / 2, 0, 0);
            //change scale if you choose a mesh with a different size
            wind.scale.set(SCALE, SCALE, SCALE);

            wind.castShadow = true;
            wind.receiveShadow = true;
            scene.add(wind);

            console.log("wind loaded");
        };

        loadMesh('wind', loadListener);
    }

    function loadMesh(path, loadListener) {
        var loader = new THREE.STLLoader();
        loader.addEventListener('load', loadListener);
        loader.load(path);
    }

    function addShadowedLight(x, y, z, color, intensity) {

        var light = new THREE.DirectionalLight(color, intensity);
        light.position.set(x, y, z);
        scene.add(light);

        //light.castShadow = true;
        light.shadowCameraVisible = true;

        var d = 2;
        light.shadowCameraLeft = -d;
        light.shadowCameraRight = d;
        light.shadowCameraTop = d;
        light.shadowCameraBottom = -d;

        light.shadowCameraNear = 1;
        light.shadowCameraFar = 4;

        light.shadowMapWidth = 1024;
        light.shadowMapHeight = 1024;

        light.shadowBias = -0.005;
        light.shadowDarkness = 0.15;
    }
    
    function addStats() {
        // stats
        stats = new Stats();
        stats.domElement.style.position = 'absolute';
        stats.domElement.style.bottom = '0px';
        stats.domElement.style.right = '0px';
        container.appendChild(stats.domElement);
    }

    function onWindowResize() {

        camera.aspect = window.innerWidth / window.innerHeight;
        camera.updateProjectionMatrix();

        renderer.setSize(window.innerWidth, window.innerHeight);
    }

    function animate() {

        requestAnimationFrame(animate);
        if (typeof globe !== "undefined") {
            globe.rotation.z += 0.005;
            wind.rotation.z += 0.005;
        }
        renderer.render(scene, camera);
        stats.update();
    }
    
    function checkWebgl() {
        if (!Detector.webgl){
            Detector.addGetWebGLMessage();
        }
    }
    
    function updateScene(type, callback) {
        if(scene){
            scene.remove(globe);
            var f = function() {
              callback();
              wind.rotation.z = 0.0;
            };
            loadGlobe(type, f);
        }
    }

    /** public visible */
    return {
        run: function(type, loadedCallback) {
            checkWebgl();
            createContainer();
            loadAll(type, loadedCallback);
            animate();
        },
        update: function(type, loadedCallback){
            checkWebgl();
            updateScene(type, loadedCallback);
        }
    };
})();



