<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Jetstreams</title>
    </head>
    <body>
         <h1>Hello Jetstreams!</h1>

        <form  method="post" enctype="multipart/form-data" action="upload.do">
            <input name="file" type="file"/>
            <input type="submit" value="upload" />
        </form>
    </body>
</html>
