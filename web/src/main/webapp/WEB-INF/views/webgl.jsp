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
        
        <script src="js/jquery/jquery-1.11.1.min.js"></script>
        
        <script src="js/jetstreams.js"></script>            
        
        <script>
            $(function() {
                Jetstreams.run();
            });
        </script>
    </body>
</html>
