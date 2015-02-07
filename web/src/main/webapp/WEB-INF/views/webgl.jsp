<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Jetstreams STL</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
        <link type="text/css" rel="stylesheet" href="css/webgl.css"/>
    </head>
    <body>


        <div id="info">
            <h3><a href="http://threejs.org" target="_blank">three.js</a> - Model of Jetstreams</h3>
            Mouse: change camera position<br/>
            S: Start|Stop rotation<br/>
            R: rotate right<br/>
            L: rotate left
            <div id="control">
                <input type="radio" name="rd" id="rd1" value="f" onclick="UI.update('fs')"/> 
                <label for="rd1">full</label>
                <input type="radio" name="rd" id="rd2" value="w" onclick="UI.update('ws')" checked="checked"/> 
                <label for="rd2">wire</label>
            </div>
        </div>
        <div id="dlg" title="Loading...">  
            <div id="progress"></div>  
        </div>  
        <script data-main="js/conf/webgl" src="js/require.js"></script>
    </body>
</html>
