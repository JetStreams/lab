<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link type="text/css" rel="stylesheet" href="css/jquery-ui.min.css">
        <link type="text/css" rel="stylesheet" href="css/jquery-ui.theme.min.css">
        <link type="text/css" rel="stylesheet" href="css/primeui-1.1-min.css">
        <title>Jetstreams</title>
        <style>
            body{
                margin: 50px;
            }
        </style>
    </head>
    <body>
        <h1>Hello Jetstreams!</h1>

        <form  method="post" enctype="multipart/form-data" action="upload.do">
            <input name="file" type="file" class="upload"/>
            <input type="submit" value="upload" class="upload"/>
        </form>
        
        
        <script src="js/jquery/jquery-1.11.1.min.js"></script>
        <script src="js/jquery/jquery-ui.min.js"></script>
        <script src="js/primeui/primeui-1.1-min.js"></script>
        <script>
            $(function() {
               $('.upload').puibutton(); 
            });
        </script>
    </body>
</html>
