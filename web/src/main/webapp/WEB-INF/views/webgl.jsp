<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>three.js webgl - STL</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
        <link type="text/css" rel="stylesheet" href="css/style.css"/>
    </head>
    <body>
        <div id="info">
            <a href="http://threejs.org" target="_blank">three.js</a> - Model of Jetstreams
        </div>

        <script src="js/threejs/three.min.js"></script>

        <script src="js/threejs/STLLoader.js"></script>
        <script src="js/threejs/TypedGeometry.js"></script>

        <script src="js/threejs/Detector.js"></script>
        <script src="js/threejs/stats.min.js"></script>

        <script>
            run();

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

                    loadMesh('model.stl');
                    //loadMesh(path);

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
                        
                        mesh.scale.set(.0000008, .0000008, .0000008);

                        mesh.castShadow = true;
                        mesh.receiveShadow = true;

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
                    if (typeof mesh != "undefined") {
                        mesh.rotation.z += 0.005;
                    }
                    renderer.render(scene, camera);
                    stats.update();
                }
            }

        </script>
    </body>
</html>
