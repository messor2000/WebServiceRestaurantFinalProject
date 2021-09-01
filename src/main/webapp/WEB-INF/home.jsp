<%--
  Created by IntelliJ IDEA.
  User: root-lp
  Date: 29.08.2021
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="epam.task.finaltaskepam.dto.AppUser" %>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
         pageEncoding="US-ASCII" %>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">--%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Home Page</title>
</head>
<body>
<%AppUser user = (AppUser) session.getAttribute("User"); %>
<h3>Hi <%=user.getUsername() %>
</h3>
<strong>Your Username</strong>: <%=user.getUsername() %><br>
<strong>Your Email</strong>: <%=user.getEmail() %><br>
<br>
<form action="Logout" method="post">
    <input type="submit" value="Logout">
</form>
</body>
</html>
