<%-- 
    Document   : login
    Created on : 10-Nov-2017, 8:26:48 AM
    Author     : awarsyle
--%>

<%@include file="/WEB-INF/jspf/header.jspf" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>NotesKeeper Login</h1>
        <form action="login" method="post">
            username: <input type="text" name="username" value="${username}"><br>
            password: <input type="${ret == 1 ? 'text': 'password'}" name="password" value="${password}"><br>
            <input type="submit" value="Login">
        </form>
        ${errormessage}
        
         <a href="<c:url value='forgot'></c:url>">Forgot Password</a><br />
    </body>
</html>
