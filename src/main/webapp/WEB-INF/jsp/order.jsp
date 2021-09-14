<%--
  Created by IntelliJ IDEA.
  User: olexandr
  Date: 09.09.2021
  Time: 20:03
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.allUsers" var="allUsers"/>
<fmt:message bundle="${locale}" key="locale.username" var="username"/>
<fmt:message bundle="${locale}" key="locale.email" var="email"/>
<fmt:message bundle="${locale}" key="locale.password" var="password"/>
<fmt:message bundle="${locale}" key="locale.role" var="role"/>
<fmt:message bundle="${locale}" key="locale.purse" var="purse"/>
<fmt:message bundle="${locale}" key="locale.dishName" var="name"/>
<fmt:message bundle="${locale}" key="locale.dishPrice" var="price"/>
<fmt:message bundle="${locale}" key="locale.category" var="category"/>

<!DOCTYPE html>
<html>
<head>
    <title>Order</title>
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
        document.getElementById("user").className = "active";
    }
</script>
<div class="container-fluid text-center wrapper">
    <div class="row content">

        <c:import url="template/leftbar.jsp"/>

        <div class="col-sm-8 text-left mainContent">
            <h1>Order</h1>

            <div class="col-sm-8 text-left mainContent">
                <div class="col-sm-6">
                    <c:forEach items="${requestScope.menu}" var="dish">
                        <div class="col-sm-6">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title card-Title-Found">${dish.name}</h5>
                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item">${price}:${dish.price}</li>
                                        <li class="list-group-item">${category}:${dish.category}</li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <a href="FrontController?command=pay">
                        <button type="button" class="btn btn-default"
                                data-dismiss="modal">Оплатить
                        </button>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<c:import url="template/footer.jsp"/>
</body>
</html>

