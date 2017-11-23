<%-- 
    Document   : retrievepassword
    Created on : Nov 23, 2017, 1:30:16 PM
    Author     : 733196
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Retrieve Password</title>
    </head>
    <body>
         <form action="login" method="post">
            username: ${username}<br/>
            password: ${password}<br/>
            <input type="submit" value="Login">
        </form>
        ${errormessage}
    </body>
</html>
