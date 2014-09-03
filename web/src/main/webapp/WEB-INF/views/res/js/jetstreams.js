/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. 
 */
function run() {
    if (!Detector.webgl)
        Detector.addGetWebGLMessage();

    var container, stats;
    var camera, scene, renderer, mesh;

    init();
    animate();

    function init() {

        //create and add container
        container = document.createElement('div');
        document.body.appendChild(container);

        camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 1, 15);
        camera.position.z = 10;

        scene = new THREE.Scene();

        var path = 'download.do';
        loadMesh(path);

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

        // stats

        stats = new Stats();
        stats.domElement.style.position = 'absolute';
        stats.domElement.style.top = '0px';
        container.appendChild(stats.domElement);

        window.addEventListener('resize', onWindowResize, false);
    }

    function loadMesh(path) {
        var material = new THREE.MeshLambertMaterial({ambient: 0x555555, color: 0xAAAAFF});
        var loader = new THREE.STLLoader();
        loader.addEventListener('load', function(event) {
            var geometry = event.content;
            mesh = new THREE.Mesh(geometry, material);
            mesh.rotation.set(-Math.PI / 2, 0, 0);
            //change scale if you choose a mesh with a different size
            mesh.scale.set(.0000008, .0000008, .0000008);

            mesh.castShadow = true;
            mesh.receiveShadow = true;

            console.log("mesh loaded");

            scene.add(mesh);
        });
        loader.load(path);
    }

    function addShadowedLight(x, y, z, color, intensity) {

        var light = new THREE.DirectionalLight(color, intensity);
        light.position.set(x, y, z)
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

    function onWindowResize() {

        camera.aspect = window.innerWidth / window.innerHeight;
        camera.updateProjectionMatrix();

        renderer.setSize(window.innerWidth, window.innerHeight);
    }

    function animate() {

        requestAnimationFrame(animate);
        if (typeof mesh !== "undefined") {
            mesh.rotation.z += 0.005;
        }
        renderer.render(scene, camera);
        stats.update();
    }
}



