<%--
  Created by IntelliJ IDEA.
  User: olexandr
  Date: 01.09.2021
  Time: 18:19
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.errorPage" var="errorPage"/>
<fmt:message bundle="${locale}" key="locale.return" var="returnToMainPage"/>

<!DOCTYPE html>
<html>
<head>
    <title>${errorPage}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>

<div class="container-fluid text-center wrapper">
    <div class="row content">
        <div class="col-sm-8 text-left mainContent">
            <c:if test="${requestScope.get('errorMessage')!=null}">
                <h3 class="red"><c:out value="${requestScope.get('errorMessage')}"/></h3>
                <c:remove var="errorMessage" scope="request"/>
            </c:if>
        </div>

        <div class="modal-footer">
            <a href="index.jsp">
                <button type="button" class="btn btn-default" data-dismiss="modal">${returnToMainPage}</button>
            </a>
        </div>
    </div>
</div>
</body>
</html>
