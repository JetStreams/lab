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
            .fileContainer {
                overflow: hidden;
                position: relative;
            }

            .fileContainer [type=file] {
                cursor: inherit;
                display: block;
                filter: alpha(opacity=0);
                opacity: 0;
                position: absolute;
                right: 0;
                text-align: right;
                top: 0;
            }

            .fileContainer {
                float: left;
                padding: .2em;
            }

            .fileContainer [type=file] {
                cursor: pointer;
            }
        </style>
    </head>
    <body>
        <h1>Welcome to Jetstreams!</h1>

        <form  method="post" enctype="multipart/form-data" action="upload.do">
            <div id="panel" title="Wind Data">

                <div>
                    <!-- filename to display to the user -->
                    <p id="file-name"></p>

                    <input  id="file-type" type="file" size="4" name="file" style="display: none"/>

                    <button id="browse-click" type="button" class="button">Browse for files</button>

                </div>

                <label class="pui-button ui-widget ui-state-default ui-corner-all pui-button-text-icon-left fileContainer"
                       style="margin-top: 2px">
                    Select file
                    <input id="file" name="file" type="file" accept="text/*"/>
                </label>
                <button id="upload">Upload</button>
                <p id="file-name"></p>
            </div>
        </form>


        <jsp:include page="WEB-INF/views/includes/scripts.jsp"/>
        <script>
            $(function() {

                var intervalFunc = function() {
                    var type = $('#file-type').val();
                    if(type !== "undefined"){
                        type = type.replace("C:\\fakepath\\", "");
                    }
                    $('#file-name').html(type);
                };
                $('#browse-click').on('click', function() {
                    $('#file-type').click();
                    setInterval(intervalFunc, 1);
                    return false;
                });

                $('#panel').puipanel();
                $('#browse-click').puibutton();
                $('#upload').puibutton({
                    icon: 'ui-icon-transferthick-e-w'
                });
            });
        </script>
    </body>
</html>
