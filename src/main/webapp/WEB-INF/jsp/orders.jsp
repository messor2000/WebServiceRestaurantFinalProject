<%--
  Created by IntelliJ IDEA.
  User: olexandr
  Date: 10.09.2021
  Time: 18:22
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
<fmt:message bundle="${locale}" key="locale.dishPrice" var="price"/>
<fmt:message bundle="${locale}" key="locale.category" var="category"/>
<fmt:message bundle="${locale}" key="locale.orders" var="orders"/>
<fmt:message bundle="${locale}" key="locale.orderNumber" var="orderNumber"/>
<fmt:message bundle="${locale}" key="locale.status" var="status"/>
<fmt:message bundle="${locale}" key="locale.userId" var="userId"/>
<fmt:message bundle="${locale}" key="locale.creationDate" var="creationDate"/>
<fmt:message bundle="${locale}" key="locale.approve" var="approve"/>

<!DOCTYPE html>
<html>
<head>
    <title>${orders}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/first.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
            <h1>${orders}</h1>

            <div class="col-sm-8 text-left mainContent">
                <div class="row">
                    <c:forEach items="${requestScope.order}" var="order">
                        <div class="col-sm-6">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">${orderNumber}:${order.orderId}</h5>
                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item">${status}:${order.orderStatus}</li>
                                        <li class="list-group-item">${userId}:${order.userId}</li>
<%--                                        <li class="list-group-item">${creationDate}:${order.creationDate}</li>--%>
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
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-3 col-sm-7">
                                <a href="FrontController?command=approve-order&orderId=${order.orderId}">
                                    <button type="button" class="btn btn-default"
                                            data-dismiss="modal">${approve}
                                    </button>
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
<c:import url="template/footer.jsp"/>
</body>
</html>
