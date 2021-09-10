<%--
  Created by IntelliJ IDEA.
  User: olexandr
  Date: 09.09.2021
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.cancel" var="cancel"/>
<fmt:message bundle="${locale}" key="locale.purse" var="purse"/>

<!DOCTYPE html>
<html>
<head>
    <title>Add dish</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/first.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="shortcut icon" href="images/main/favicon_16x16.png">

</head>
<body onload="active()">

<c:import url="template/navbar.jsp"/>
<script language="javascript">
    function active() {
        document.getElementById("dish").className = "active";
    }
</script>
<div class="container-fluid text-center wrapper">
    <div class="row content">

        <c:import url="template/leftbar.jsp"/>

        <div class="col-sm-8 text-left mainContent">
            <h1>Add dish</h1>

            <div class="col-sm-8 text-left mainContent">

                <div class="col-sm-7">
                    <form name="addDishForm" class="form-horizontal" method="post" action="FrontController">
                        <input type="hidden" name="command" value="add-dish"/>
                        <div class="form-group">
                            <label for="name" class="col-sm-3 control-label">Dish name</label>
                            <span id="name" class="red"></span>
                            <div class="col-sm-7">
                                <label for="dishName"></label>
                                <input type="text" class="form-control" id="dishName" placeholder="name"
                                                                     name="name" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="price" class="col-sm-3 control-label">Dish price</label>
                            <span id="price" class="red"></span>
                            <div class="col-sm-7">
                                <label for="dishPrice"></label>
                                <input type="text" class="form-control" id="dishPrice" placeholder="price"
                                       name="price" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="category" class="col-sm-3 control-label">Dish category</label>
                            <span id="category" class="red"></span>
                            <div class="col-sm-7">
                                <label for="dishCategory"></label>
                                <input type="text" class="form-control" id="dishCategory" placeholder="category"
                                       name="category" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="amount" class="col-sm-3 control-label">Dish amount</label>
                            <span id="amount" class="red"></span>
                            <div class="col-sm-7">
                                <label for="dishAmount"></label>
                                <input type="text" class="form-control" id="dishAmount" placeholder="amount"
                                       name="amount" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-3 col-sm-7">
                                <button type="submit" class="btn btn-primary">Добавить</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<c:import url="template/footer.jsp"/>
</body>
</html>
