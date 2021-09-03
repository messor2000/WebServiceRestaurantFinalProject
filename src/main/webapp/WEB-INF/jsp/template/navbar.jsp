<%--
  Created by IntelliJ IDEA.
  User: olexandr
  Date: 02.09.2021
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.siteName" var="siteName"/>
<fmt:message bundle="${locale}" key="locale.home" var="home"/>
<fmt:message bundle="${locale}" key="locale.signUp" var="signUp"/>
<fmt:message bundle="${locale}" key="locale.logIn" var="logIn"/>
<fmt:message bundle="${locale}" key="locale.logOut" var="logOut"/>
<fmt:message bundle="${locale}" key="locale.signIn" var="signIn"/>
<fmt:message bundle="${locale}" key="locale.username" var="username"/>
<fmt:message bundle="${locale}" key="locale.password" var="password"/>
<fmt:message bundle="${locale}" key="locale.cancel" var="cancel"/>
<fmt:message bundle="${locale}" key="locale.email" var="email"/>
<fmt:message bundle="${locale}" key="locale.repeatPassword" var="repeatPassword"/>
<fmt:message bundle="${locale}" key="locale.register" var="register"/>


<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">${siteName}</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li id="index-page"><a href="${pageContext.request.contextPath}/index.jsp">${home}</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${sessionScope.get('user') == null}">
                    <li class="sign-up">
                        <a data-toggle="modal" data-target="#register" href="#">
                            <span class="glyphicon glyphicon-user"></span>
                                ${signUp}</a>
                    </li>
                    <li><a data-toggle="modal" data-target="#login" href="#">
                        <span class="glyphicon glyphicon-log-in"></span>
                            ${logIn}</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.get('user') != null}">
                    <li class="sign-up">
                        <a href="FrontController?command=view-user&username=${sessionScope.get('user').username}">
                            <span class="glyphicon glyphicon-user"></span> ${sessionScope.get('user').username}</a>
                    </li>
                    <li><a href="FrontController?command=log-out">
                        <span class="glyphicon glyphicon-log-out"></span> ${logOut}</a>
                    </li>
                </c:if>
                <li><a href="FrontController?command=change-language&language=en">English</a></li>
                <li><a href="FrontController?command=change-language&language=ru">Pусский</a></li>
            </ul>
        </div>
    </div>
</nav>

<div id="login" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;  </button>
                <h4 class="modal-title">${movieRateEntrance}</h4>
            </div>
            <div class="modal-body text-center">
                <form name="loginForm" class="form-horizontal" method="post" action="FrontController" onsubmit="return validateForm();">
                    <input type="hidden" name="command" value="login"/>
                    <span id="unameDemo" class="red"></span>
                    <div class="form-group">
                        <label for="username" class="col-sm-3 control-label">${username}</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="username" placeholder="${username}"
                                   name="username" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password3" class="col-sm-3 control-label">${password}</label>
                        <span id="pswDemo" class="red"></span>
                        <div class="col-sm-7">
                            <input type="password" class="form-control" id="password3" placeholder="${password}"
                                   name="pass" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-7">
                            <button type="submit" class="btn btn-primary">${signIn}</button>
                        </div>
                    </div>
                </form>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">${cancel}</button>
            </div>
        </div>
    </div>
</div>

<script language="javascript">
    function validateForm() {
        let uname, psw;
        let unameText, pswText;
        let result = true;
        uname = document.forms["loginForm"]["username"].value;
        psw = document.forms["loginForm"]["pass"].value;
        const unamePattern = /[a-zA-Z_0-9]{3,16}/;
        if (!unamePattern.test(uname)) {
            unameText = "Username should contain only latin symbols, digits and _";
            document.getElementById("unameDemo").innerHTML = unameText;
            result = false;
        } else if (uname.length < 3) {
            unameText = "Username should be at least 3 symbols.";
            document.getElementById("unameDemo").innerHTML = unameText;
            result = false;
        } else if (uname.length > 16) {
            unameText = "Username should be less then 17 symbols.";
            document.getElementById("unameDemo").innerHTML = unameText;
            result = false;
        } else {
            unameText = "";
            document.getElementById("unameDemo").innerHTML = unameText;
        }
        const passPattern = /[a-zA-Z0-9_]{6,32}/;
        if (psw.length < 6) {
            pswText = "Password should be at least 6 symbols";
            document.getElementById("pswDemo").innerHTML = pswText;
            result = false;
        } else if (!passPattern.test(psw)) {
            pswText = "Password should contain only latin symbols, digits and _";
            document.getElementById("pswDemo").innerHTML = pswText;
            result = false;
        } else if (psw.length > 32) {
            pswText = "Password should be less then 32 symbols";
            document.getElementById("pswDemo").innerHTML = pswText;
            result = false;
        }
        return result;
    }
</script>

<div id="register" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;  </button>
                <h4 class="modal-title" id="myModalLabel">${movieRateRegister}</h4>
            </div>
            <div class="modal-body text-center">
                <form name="registerForm" class="form-horizontal" method="post" action="FrontController"  onsubmit="return validateReg();">
                    <input type="hidden" name="command" value="register"/>
                    <div class="form-group">
                        <label for="inputUsername" class="col-sm-3 control-label">${username}</label>
                        <span id="unameDemo2" class="red"></span>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="inputUsername" placeholder="${username}"
                                   name="username" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-sm-3 control-label">${email}</label>
                        <span id="emailDemo2" class="red"></span>
                        <div class="col-sm-7">
                            <input type="email" class="form-control" id="email" placeholder="${email}"
                                   name="email" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-3 control-label">${password}</label>
                        <span id="pswDemo2" class="red"></span>
                        <div class="col-sm-7">
                            <input type="password" class="form-control" id="inputPassword3" placeholder="${password}"
                                   name="pass" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword4" class="col-sm-3 control-label">${repeatPassword}</label>
                        <div class="col-sm-7">
                            <input type="password" class="form-control" id="inputPassword4" placeholder="${repeatPassword}"
                                   name="pass2" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-7">
                            <button type="submit" class="btn btn-primary">${register}</button>
                        </div>
                    </div>
                </form>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">${cancel}</button>
            </div>
        </div>
    </div>
</div>
<script  language="javascript">
    function validateReg() {
        let uname, email, psw, psw2;
        let unameText, emailText, pswText;
        let result = true;
        uname = document.forms["registerForm"]["nickname"].value;
        email = document.forms["registerForm"]["email"].value;
        psw = document.forms["registerForm"]["pass"].value;
        psw2 = document.forms["registerForm"]["pass2"].value;
        const unamePattern = /[a-zA-Z_0-9]{3,16}/;
        if (!unamePattern.test(uname)) {
            unameText = "Nickname should contain only latin symbols, digits and _";
            document.getElementById("unameDemo2").innerHTML = unameText;
            result = false;
        } else if (uname.length < 3) {
            unameText = "Nickname should be at least 3 symbols.";
            document.getElementById("unameDemo2").innerHTML = unameText;
            result = false;
        } else if (uname.length > 16) {
            unameText = "Nickname should be less then 17 symbols.";
            document.getElementById("unameDemo2").innerHTML = unameText;
            result = false;
        } else {
            unameText = "";
            document.getElementById("unameDemo2").innerHTML = unameText;
        }
        const emailPattern = /[a-zA-Z0-9_]+@[A-Za-z0-9].+/;
        if (!emailPattern.test(email)) {
            emailText = "Email should contain latin symbols, @, digits, . and _";
            document.getElementById("emailDemo2").innerHTML = emailText;
            return false;
        } else {
            unameText = "";
            document.getElementById("emailDemo2").innerHTML = unameText;
        }
        const passPattern = /[a-zA-Z0-9_]{6,32}/;
        if (psw.length < 6) {
            pswText = "Password should be at least 6 symbols";
            document.getElementById("pswDemo2").innerHTML = pswText;
            result = false;
        } else if (!passPattern.test(psw)) {
            pswText = "Password should contain only latin symbols, digits and _";
            document.getElementById("pswDemo2").innerHTML = pswText;
            result = false;
        } else if (psw.length > 32) {
            pswText = "Password should be less then 32 symbols";
            document.getElementById("pswDemo2").innerHTML = pswText;
            result = false;
        } else if (psw !== psw2) {
            pswText = "Passwords should be the same";
            document.getElementById("pswDemo2").innerHTML = pswText;
            result = false;
        } else {
            unameText = "";
            document.getElementById("pswDemo2").innerHTML = unameText;
        }
        return result;
    }
</script>
