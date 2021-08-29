<%--
  Created by IntelliJ IDEA.
  User: root-lp
  Date: 27.08.2021
  Time: 23:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="US-ASCII">
    <title>Login Page</title>
</head>
<body>
<h3>Login with email and password</h3>
<form action="Login" method="post">
    <strong>User Email</strong>:<input type="text" name="email"><br>
    <strong>Password</strong>:<input type="password" name="password"><br>
    <input type="submit" value="Login">
</form>
<br>
If you are new user, please <a href="registration.jsp">register</a>.
</body>
</html>
