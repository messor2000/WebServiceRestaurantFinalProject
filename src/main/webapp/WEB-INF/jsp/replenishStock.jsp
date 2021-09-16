<%--
  Created by IntelliJ IDEA.
  User: olexandr
  Date: 09.09.2021
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.cancel" var="cancel"/>
<fmt:message bundle="${locale}" key="locale.purse" var="purse"/>
<fmt:message bundle="${locale}" key="locale.topUp" var="topUp"/>
<fmt:message bundle="${locale}" key="locale.replenishStock" var="replenishStock"/>

<!DOCTYPE html>
<html>
<head>
    <title>${replenishStock}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/first.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body onload="active()">

<c:import url="template/navbar.jsp"/>
<script language="javascript">
    function active() {
        document.getElementById("purse").className = "active";
    }
</script>
<div class="container-fluid text-center wrapper">
    <div class="row content">

        <c:import url="template/leftbar.jsp"/>

        <div class="col-sm-8 text-left mainContent">
            <h1>${replenishStock}</h1>

            <div class="col-sm-8 text-left mainContent">
                <div class="col-sm-7">
                    <form name="replenishStock" class="form-horizontal" method="post" action="FrontController">
                        <input type="hidden" name="command" value="replenish-stock"/>
                        <div class="replenishStock" >
                            <div class="form-group">
                                <span id="amount" class="red"></span>
                                <div class="col-sm-7 input-amount">
                                    <label for="stockAmount"></label>
                                    <input type="text" class="form-control" id="stockAmount" placeholder=${topUp}
                                           name="amount" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-7 batton-submit">
                                    <button type="submit" class="btn btn-primary">${topUp}</button>
                                </div>
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



