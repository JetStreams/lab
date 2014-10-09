<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <jsp:include page="WEB-INF/views/includes/styles.jsp"/>
        <title>Jetstreams</title>
        <style>
            body{
                margin: 50px;
                font-family: Sans-Serif;
            }
        </style>
    </head>
    <body>
        <h1>Welcome to Jetstreams 3D simulation.</h1>

        <form  method="post" enctype="multipart/form-data" action="upload.do">
            <div id="panel" title="Wind Data">
                <div>
                    <input  id="file-type" type="file" size="4" name="file" 
                            accept="text/*" style="display: none"/>
                    <button id="browse-click" type="button" class="button">Select file</button>
                    <!-- filename to display to the user -->
                    <p id="file-name"></p>
                </div>
                <button id="upload">Upload</button>
            </div>
        </form>

        <div style="margin-top: 25px">
            <h4>Data Samples</h4>
            <ul>
                <li><a href="txt/wind_north.txt">Northern Hemisphere</a></li>
                <li><a href="txt/wind_south.txt">Southern Hemisphere</a></li>
            </ul>
        </div>

        <jsp:include page="WEB-INF/views/includes/scripts.jsp"/>
        <script src="js/upload.js"></script>  
        <script>
            $(function() {
                UI.create();
            });
        </script>
    </body>
</html>
