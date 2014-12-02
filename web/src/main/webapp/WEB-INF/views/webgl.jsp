<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Jetstreams STL</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
        <link type="text/css" rel="stylesheet" href="css/style.css"/>
        <jsp:include page="includes/styles.jsp"/>
    </head>
    <body>


        <div id="info">
            <a href="http://threejs.org" target="_blank">three.js</a> - Model of Jetstreams
            <div id="control">
                <input type="radio" name="rd" id="rd1" value="f" onclick="UI.update('fs')"/> 
                <label for="rd1">full</label>
                <input type="radio" name="rd" id="rd2" value="w" onclick="UI.update('ws')" checked="checked"/> 
                <label for="rd2">wire</label>
            </div>
            <!--button id="full" type="button">Full</button--> 
        </div>

        <script src="js/threejs/three.min.js"></script>
        <script src="js/threejs/STLLoader.js"></script>
        <script src="js/threejs/TypedGeometry.js"></script>
        <script src="js/threejs/Detector.js"></script>
        <script src="js/threejs/stats.min.js"></script>

        <jsp:include page="includes/scripts.jsp"/>

        <script src="js/jetstreams.js"></script>  
        <script src="js/download.js"></script>  

        <script>
            $(function () {
                UI.create('ws');
            });
        </script>
        <div id="dlg" title="Loading...">  
            <div id="progress"></div>  
        </div>  
    </body>
</html>
