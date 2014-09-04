<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Jetstreams STL</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
        <link type="text/css" rel="stylesheet" href="css/style.css"/>
        <link type="text/css" rel="stylesheet" href="css/jquery-ui.min.css">
        <link type="text/css" rel="stylesheet" href="css/jquery-ui.theme.min.css">
        <link type="text/css" rel="stylesheet" href="css/primeui-1.1-min.css">
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
        <script src="js/jquery/jquery-ui.min.js"></script>
        <script src="js/primeui/primeui-1.1-min.js"></script>

        <script src="js/jetstreams.js"></script>            

        <script>
            $(function() {
                //setup progress bar
                //TODO: it might be better to use jquery's indeterminate 
                //progressbar when loading takes too long
                $("#progress").puiprogressbar({
                    labelTemplate: 'Hold tight...'
                });
                //setup dialog
                $('#dlg').puidialog({
                    closable: false,
                    minimizable: false,
                    maximizable: false
                });
                //show dialog
                $('#dlg').puidialog('show');
                
                //start progress animation
                setInterval(function() {
                    var val = $('#progress').puiprogressbar('option', 'value') + 10;
                    $('#progress').puiprogressbar('option', 'value', val);
                }, 500);

                //load stl model
                Jetstreams.run('download.do', function() {
                    $('#dlg').puidialog('hide');
                });
            });
        </script>
        <div id="dlg" title="Loading Model">  
            <div id="progress" />  
        </div>  
    </body>
</html>
