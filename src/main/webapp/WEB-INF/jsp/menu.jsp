<%--
  Created by IntelliJ IDEA.
  User: olexandr
  Date: 05.09.2021
  Time: 18:46
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.menu" var="menu"/>
<fmt:message bundle="${locale}" key="locale.dishName" var="name"/>
<fmt:message bundle="${locale}" key="locale.dishPrice" var="price"/>
<fmt:message bundle="${locale}" key="locale.category" var="category"/>
<fmt:message bundle="${locale}" key="locale.sort" var="sort"/>
<fmt:message bundle="${locale}" key="locale.allMenu" var="allMenu"/>
<fmt:message bundle="${locale}" key="locale.name" var="name"/>
<fmt:message bundle="${locale}" key="locale.highPrice" var="highPrice"/>
<fmt:message bundle="${locale}" key="locale.lowPrice" var="lowPrice"/>
<fmt:message bundle="${locale}" key="locale.fastFood" var="fastFood"/>
<fmt:message bundle="${locale}" key="locale.healthyFood" var="healthyFood"/>
<fmt:message bundle="${locale}" key="locale.desert" var="desert"/>
<%--<fmt:message bundle="${locale}" key="locale.bestMoviesForYou" var="bestMoviesForYou"/>--%>
<%--<fmt:message bundle="${locale}" key="locale.theBestMovies" var="theBestMovies"/>--%>
<%--<fmt:message bundle="${locale}" key="locale.all" var="all"/>--%>
<%--<fmt:message bundle="${locale}" key="locale.adventure" var="adventure"/>--%>
<%--<fmt:message bundle="${locale}" key="locale.crime" var="crime"/>--%>
<%--<fmt:message bundle="${locale}" key="locale.biography" var="biography"/>--%>
<%--<fmt:message bundle="${locale}" key="locale.thriller" var="thriller"/>--%>
<%--<fmt:message bundle="${locale}" key="locale.comedy" var="comedy"/>--%>
<%--<fmt:message bundle="${locale}" key="locale.melodrama" var="melodrama"/>--%>
<%--<fmt:message bundle="${locale}" key="locale.drama" var="drama"/>--%>
<%--<fmt:message bundle="${locale}" key="locale.fantasy" var="fantasy"/>--%>
<%--<fmt:message bundle="${locale}" key="locale.byDecades" var="byDecades"/>--%>
<%--<fmt:message bundle="${locale}" key="locale.byYear" var="byYear"/>--%>
<%--<fmt:message bundle="${locale}" key="locale.eightieth" var="eightieth"/>--%>
<%--<fmt:message bundle="${locale}" key="locale.ninetieth" var="ninetieth"/>--%>
<%--<fmt:message bundle="${locale}" key="locale.thousandth" var="thousandth"/>--%>
<%--<fmt:message bundle="${locale}" key="locale.tenth" var="tenth"/>--%>
<%--<fmt:message bundle="${locale}" key="locale.movie" var="movie"/>--%>
<%--<fmt:message bundle="${locale}" key="locale.yourRating" var="yourRating"/>--%>
<%--<fmt:message bundle="${locale}" key="locale.ratingVotes" var="ratingVotes"/>--%>
<%--<fmt:message bundle="${locale}" key="locale.next" var="next"/>--%>
<%--<fmt:message bundle="${locale}" key="locale.previous" var="previous"/>--%>

<!DOCTYPE html>
<html>
<head>
    <title>${menu}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/first.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="shortcut icon" href="images/main/favicon_16x16.png">

</head>
<body onload="active()">

<c:import url="template/navbar.jsp"/>
<script language="javascript">
    function active() {
        document.getElementById("menu").className = "active";
    }
</script>
<div class="container-fluid text-center wrapper">
    <div class="row content">

        <c:import url="template/leftbar.jsp"/>

        <div class="col-sm-8 text-left mainContent">
            <h1>${menu}</h1>

            <div class="row">
                <c:forEach items="${requestScope.menu}" var="dish">
                    <div class="col-sm-6">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">${dish.name}</h5>
                                <ul class="list-group list-group-flush">
                                    <li class="list-group-item">${price}:${dish.price}</li>
                                    <li class="list-group-item">${category}:${dish.category}</li>
                                </ul>
                                <c:if test='${sessionScope.get("user").role eq "customer" || sessionScope.get("user").role eq "manager"}'>
                                    <a href="#" class="btn btn-primary">Put in bucket</a>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <c:import url="template/footer.jsp"/>
</body>
</html>
