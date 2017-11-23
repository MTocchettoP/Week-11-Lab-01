<%-- 
    Document   : forgotpassword
    Created on : Nov 21, 2017, 1:28:46 PM
    Author     : 733196
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot Password</title>
    </head>
    <body>
        <form action="forgot" method="POST">
            <h1>Forgot Password</h1>
            <p>Please enter your e-mail address to retrieve your password</p>
            Email address:<input type="email" name="email"><br>
            <input type="submit" value="Submit">
        </form>
        ${errormessager}
    </body>
</html>
