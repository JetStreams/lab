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
            }
        </style>
    </head>
    <body>
        <h1>Hello Jetstreams!</h1>

        <form  method="post" enctype="multipart/form-data" action="upload.do">
            <input name="file" type="file" class="upload"/>
            <input type="submit" value="upload" class="upload"/>
        </form>
        
        
        <jsp:include page="WEB-INF/views/includes/scripts.jsp"/>
        <script>
            $(function() {
               $('.upload').puibutton(); 
            });
        </script>
    </body>
</html>
