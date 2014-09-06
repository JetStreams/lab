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

        <jsp:include page="WEB-INF/views/includes/scripts.jsp"/>
        <script src="js/fileSelect.js"></script>
        <script>
            $(function() {
                $('#panel').puipanel();
                $('#browse-click').puibutton();
                $('#upload').puibutton({
                    icon: 'ui-icon-transferthick-e-w'
                });

                var intervalFunc = function() {
                    var type = $('#file-type').val();
                    if (type !== "undefined") {
                        type = type.replace("C:\\fakepath\\", "");
                    }
                    $('#file-name').html(type);
                };
                $('#browse-click').on('click', function() {
                    $('#file-type').click();
                    setInterval(intervalFunc, 1);
                    return false;
                });
            });
        </script>
    </body>
</html>
