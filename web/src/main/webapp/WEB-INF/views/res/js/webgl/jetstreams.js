/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
var Jetstreams = (function () {
    var SCALE = .0000008;
    var SPEED = 0.005;

    var container, stats;

    var camera, scene, renderer, controls;

    var globe, wind;

    function create() {

        //create and add container
        container = document.createElement('div');
        document.body.appendChild(container);

        addStats();

        camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 1, 15);
        camera.position.z = 10;

        scene = new THREE.Scene();

        // Lights
        addShadowLight(0, 0, 10, 0xBEBEBE, 2);

        // renderer
        createRenderer();

        //controls
        createControls();

        container.appendChild(renderer.domElement);

        window.addEventListener('resize', onWindowResize, false);
    }

    function createRenderer() {
        renderer = new THREE.WebGLRenderer({antialias: true});
        renderer.setSize(window.innerWidth, window.innerHeight);
        renderer.gammaInput = true;
        renderer.gammaOutput = true;
        renderer.shadowMapEnabled = true;
        renderer.shadowMapCullFace = THREE.CullFaceBack;
    }

    function createControls() {
        controls = new THREE.TrackballControls(camera);
        controls.noPan = true;
        controls.zoomSpeed = 1;
        controls.maxDistance = 12;
       
        controls.addEventListener('change', render);
    }

    function addShadowLight(x, y, z, color, intensity) {

        var light = new THREE.DirectionalLight(color, intensity);
        light.position.set(x, y, z);
        scene.add(light);

        light.castShadow = true;
//        light.shadowCameraVisible = true;

        var d = 2;
        light.shadowCameraLeft = -d;
        light.shadowCameraRight = d;
        light.shadowCameraTop = d;
        light.shadowCameraBottom = -d;

        light.shadowCameraNear = 1;
        light.shadowCameraFar = 4;

        light.shadowBias = -0.005;
        light.shadowDarkness = 0.15;
    }

    function loadAll(type, callback) {
        loadGlobe(type, callback);
        loadWind();
    }

    function loadGlobe(type, callback) {

        var loadListener = function (event) {
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
        loadMesh('globe/' + type, loadListener);
    }

    function loadWind() {

        var loadListener = function (event) {
            var geometry = event.content;
            var material = new THREE.MeshLambertMaterial({ambient: 0x000000, color: 0xFFAAAA});
            wind = new THREE.Mesh(geometry, material);
            wind.rotation.set(-Math.PI / 2, 0, 0);
            //change scale if you choose a mesh with a different size
            wind.scale.set(SCALE, SCALE, SCALE);

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

        controls.handleResize();
    }

    function animate() {

        requestAnimationFrame(animate);
        if (typeof globe !== "undefined") {
            globe.rotation.z += SPEED;
            wind.rotation.z += SPEED;
        }
        controls.update();
        render();
    }

    function render() {
        renderer.render(scene, camera);
        stats.update();
    }

    function checkWebgl() {
        if (!Detector.webgl) {
            Detector.addGetWebGLMessage();
        }
    }

    function updateScene(type, callback) {
        if (scene) {
            scene.remove(globe);
            var f = function () {
                callback();
                wind.rotation.z = 0.0;
            };
            loadGlobe(type, f);
        }
    }

    /** public visible */
    return {
        run: function (type, loadedCallback) {
            checkWebgl();
            create();
            loadAll(type, loadedCallback);
            animate();
        },
        update: function (type, loadedCallback) {
            checkWebgl();
            updateScene(type, loadedCallback);
        }
    };
})();



