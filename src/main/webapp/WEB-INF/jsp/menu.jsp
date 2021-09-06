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

<%--            <div class="selectboxes">--%>
            <%--                <ul class="nav nav-pills">--%>
            <%--                    <li class="nav-item">--%>
            <%--                        <a class="nav-link active" aria-current="page" href="FrontController?command=show-menu">All--%>
            <%--                            menu</a>--%>
            <%--                    </li>--%>
            <%--                    <li class="nav-item dropdown">--%>
            <%--                        <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button"--%>
            <%--                           aria-expanded="false">By price</a>--%>
            <%--                        <ul class="dropdown-menu">--%>
            <%--                            <li><a class="dropdown-item" href="FrontController?command=show-menu-by-high-price">By high--%>
            <%--                                price</a></li>--%>
            <%--                            <li><a class="dropdown-item" href="FrontController?command=show-menu-by-low-price">By low--%>
            <%--                                price</a></li>--%>
            <%--                        </ul>--%>
            <%--                    </li>--%>
            <%--                    <li class="nav-item dropdown">--%>
            <%--                        <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="FrontController?command=show-menu-by-category" role="button"--%>
            <%--                           aria-expanded="false">By category</a>--%>
            <%--                        <ul class="dropdown-menu">--%>
            <%--                            <li><a class="dropdown-item"--%>
            <%--                                   href="FrontController?command=show-menu-by-category&category=fast food">Fast Food</a>--%>
            <%--                            </li>--%>
            <%--                            <li><a class="dropdown-item"--%>
            <%--                                   href="FrontController?command=show-menu-by-category&category=healthy food">Healthy--%>
            <%--                                Food</a></li>--%>
            <%--                            <li><a class="dropdown-item"--%>
            <%--                                   href="FrontController?command=show-menu-by-category&category=desert">Desert</a></li>--%>
            <%--                        </ul>--%>
            <%--                    </li>--%>
            <%--                </ul>--%>
            <%--            </div>--%>

            <div class="selectboxes">
                <div class="sortAwaits" style="width: 391px; float: left; margin: 0">
                    <span>By high price</span>
                    <label>
                        <select class="sortStat" onchange="MakeSort(this);">
                            <option value="FrontController?command=show-menu">-</option>
                            <option value="FrontController?command=show-menu-by-high-price">${lowPrice}</option>
                            <option value="FrontController?command=show-menu-by-low-price">${highPrice}</option>
                        </select>
                    </label>
                    <br>
                    <span>By category</span>
                    <label>
                        <select class="sortStat" name="y" onchange="MakeSort(this);">
                            <option value="Controller?command=all-movies">-</option>
                            <option value="FrontController?command=show-menu-by-category&category=fast food">Fast Food</option>
                            <option value="FrontController?command=show-menu-by-category&category=healthy food">Healthy Food</option>
                            <option value="FrontController?command=show-menu-by-category&category=desert">Desert</option>
                        </select>
                    </label>
                    <br>
                </div>
                <div class="clear"></div>
            </div>
            <script type="text/javascript">
                function MakeSort(element) {
                    const selected = $('option:selected', element),
                        href = selected.val();
                    if (!href) {
                        return false;
                    }
                    document.location = href;
                }
            </script>
            <br>

            <div class="col-sm-8 text-left mainContent">
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
    </div>
    <c:import url="template/footer.jsp"/>
</body>
</html>
