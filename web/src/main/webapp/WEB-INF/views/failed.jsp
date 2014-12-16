<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <jsp:include page="includes/styles.jsp"/>
        <title>Jetstreams</title>
        <style>
            body{
                margin: 50px;
                font-family: Sans-Serif;
            }
        </style>
    </head>
    <body>
        <h1>Oops.</h1>

        <div id="panel" title="Upload Failed!">
            <p>${message}</p>
            <p><a href="index.jsp">Try again</a></p>
        </div>

        <script data-main="js/app/default" src="js/require.js"></script>
    </body>
</html>
