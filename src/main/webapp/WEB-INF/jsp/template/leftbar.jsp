<%--
  Created by IntelliJ IDEA.
  User: olexandr
  Date: 05.09.2021
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.cancel" var="cancel"/>
<fmt:message bundle="${locale}" key="locale.name" var="name"/>
<fmt:message bundle="${locale}" key="locale.category" var="category"/>
<fmt:message bundle="${locale}" key="locale.amount" var="amount"/>
<fmt:message bundle="${locale}" key="locale.add" var="add"/>
<fmt:message bundle="${locale}" key="locale.purse" var="purse"/>
<fmt:message bundle="${locale}" key="locale.allUsers" var="allUsers"/>
<fmt:message bundle="${locale}" key="locale.add" var="add"/>
<fmt:message bundle="${locale}" key="locale.dishPrice" var="price"/>
<fmt:message bundle="${locale}" key="locale.add" var="addDish"/>

<div class="col-sm-2 sidenav">
    <c:if test='${sessionScope.get("user").role eq "manager"}'>
        <p><a class="manager" href="FrontController?command=show-all-users">${allUsers}</a></p>
        <%--        <p><a data-toggle="modal" data-target="#addDish" href="FrontController?command=add-dish">--%>
        <%--            <span class="glyphicon glyphicon"></span>${add}</a></p>--%>
        <p><a class="manager" href="FrontController?command=add-dish">${addDish}</a></p>
        <p><a class="manager" href="FrontController?command=replenish-stock">Replenish Stock</a></p>
    </c:if>
    <c:if test='${sessionScope.get("user").role eq "manager" || sessionScope.get("user").role eq "customer"}'>
        <p><a class="manager" href="FrontController?command=show-purse">${purse}</a></p>
    </c:if>
</div>
