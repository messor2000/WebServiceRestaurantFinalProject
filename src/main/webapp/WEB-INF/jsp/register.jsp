<%--
  Created by IntelliJ IDEA.
  User: olexandr
  Date: 01.09.2021
  Time: 22:46
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set scope="session" var="previousQuery" value="index.jsp"/>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.username" var="username"/>
<fmt:message bundle="${locale}" key="locale.password" var="password"/>
<fmt:message bundle="${locale}" key="locale.cancel" var="cancel"/>
<%--<fmt:message bundle="${locale}" key="locale.movieRateRegister" var="movieRateRegister"/>--%>
<fmt:message bundle="${locale}" key="locale.email" var="email"/>
<fmt:message bundle="${locale}" key="locale.repeatPassword" var="repeatPassword"/>
<fmt:message bundle="${locale}" key="locale.other" var="other"/>
<fmt:message bundle="${locale}" key="locale.register" var="register"/>
<!DOCTYPE html>
<html>
<head>
    <title>${register}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/first.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<%--    <link rel="shortcut icon" href="images/main/favicon_16x16.png">--%>


</head>
<body onload="active()">

<%--<c:import url="template/navbar.jsp"/>--%>
<script language="javascript">
    function active() {
        document.getElementById("index-page").className = "active";
    }
</script>
<div class="container-fluid text-center wrapper">
    <div class="row content">

<%--        <c:import url="template/sideleft.jsp"/>--%>

        <div class="col-sm-8 text-left mainContent">

            <div class="modal-dialog">
                <div class="modal-content">
                    <c:if test="${requestScope.get('errorMessage')!=null}">
                        <h4 class="red text-center"><c:out value="${requestScope.get('errorMessage')}"/></h4>
                        <c:remove var="errorMessage" scope="request"/>
                    </c:if>
                    <div class="modal-body text-center">
                        <form name="registerForm" class="form-horizontal" method="post" action="FrontController" onsubmit="return validateForm();">
                            <input type="hidden" name="command" value="register"/>
                            <div class="form-group">
                                <label for="inputUsername" class="col-sm-3 control-label">${username}</label>
                                <span id="unameDemo" class="red"></span>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" id="inputUsername" placeholder="${username}"
                                           name="username" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="email" class="col-sm-3 control-label">${email}</label>
                                <span id="emailDemo" class="red"></span>
                                <div class="col-sm-7">
                                    <input type="email" class="form-control" id="email" placeholder="${email}"
                                           name="email" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputPassword3" class="col-sm-3 control-label">${password}</label>
                                <span id="pswDemo" class="red"></span>
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

<%--        <c:import url="template/sideright.jsp"/>--%>

    </div>
</div>

<c:import url="template/footer.jsp"/>

<script  language="javascript">
    function validateForm() {
        let uname, email, psw, psw2;
        let unameText, emailText, pswText;
        let result = true;
        uname = document.forms["registerForm"]["username"].value;
        email = document.forms["registerForm"]["email"].value;
        psw = document.forms["registerForm"]["pass"].value;
        psw2 = document.forms["registerForm"]["pass2"].value;
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
        }  else {
            unameText = "";
            document.getElementById("unameDemo").innerHTML = unameText;
        }
        const emailPattern = /[a-zA-Z0-9_]+@[A-Za-z0-9].+/;
        if (!emailPattern.test(email)) {
            emailText = "Email should contain latin symbols, @, digits, . and _";
            document.getElementById("emailDemo").innerHTML = emailText;
            return false;
        } else {
            unameText = "";
            document.getElementById("emailDemo").innerHTML = unameText;
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
        } else if (psw !== psw2) {
            pswText = "Passwords should be the same";
            document.getElementById("pswDemo").innerHTML = pswText;
            result = false;
        } else {
            unameText = "";
            document.getElementById("pswDemo").innerHTML = unameText;
        }
        return result;
    }
</script>
</body>
</html>
