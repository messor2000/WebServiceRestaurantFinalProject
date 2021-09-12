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
<fmt:message bundle="${locale}" key="locale.searchLabel" var="searchLabel"/>
<fmt:message bundle="${locale}" key="locale.searchTitle" var="searchTitle"/>
<fmt:message bundle="${locale}" key="locale.search" var="search"/>
<fmt:message bundle="${locale}" key="locale.byPrice" var="byPrice"/>
<fmt:message bundle="${locale}" key="locale.byCategory" var="byCategory"/>
<fmt:message bundle="${locale}" key="locale.cancel" var="cancel"/>

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
    <%--    <script src="../../js/test.js"></script>--%>
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
            <div class="selectboxes">
                <br>
                <form method="get" action="FrontController" class="form-horizontal">
                    <input type="hidden" name="command" value="find-dish"/>
                    <div class="form-group">
                        <label for="search" class="col-sm-2 control-label">${searchLabel}</label>
                        <div class="col-sm-8" onchange="MakeSort(this);">
                            <input id="search" title="${searchTitle}" type="text" class="form-control"
                                   placeholder="${menu}" name="name"/>
                            <br/>
                        </div>
                        <div class="col-sm-2">
                            <button type="submit" class="btn btn-success">${search}</button>
                        </div>
                    </div>

                </form>
                <hr>

                <div class="sortAwaits" style="width: 391px; float: left; margin: 0">
                    <span>${byPrice}</span>
                    <label>
                        <select class="sortStat" onchange="MakeSort(this);">
                            <option value="FrontController?command=show-menu">-</option>
                            <option value="FrontController?command=show-menu-by-low-price">${lowPrice}</option>
                            <option value="FrontController?command=show-menu-by-high-price">${highPrice}</option>
                        </select>
                    </label>
                    <br>
                    <span>${byCategory}</span>
                    <label>
                        <select class="sortStat" name="y" onchange="MakeSort(this);">
                            <option value="Controller?command=all-movies">-</option>
                            <option value="FrontController?command=show-menu-by-category&category=fast food">${fastFood}</option>
                            <option value="FrontController?command=show-menu-by-category&category=healthy food">${healthyFood}</option>
                            <option value="FrontController?command=show-menu-by-category&category=desert">${desert}</option>
                        </select>
                    </label>
                    <br>

                    <c:if test='${sessionScope.get("user").role eq "manager"}'>
                        <ol><a data-toggle="modal" data-target="#replenish-stock" href="#">
                            <span class="glyphicon"></span>Replenish stock of dishes</a>
                        </ol>
                    </c:if>
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
                                    <h5 class="card-title card-Title-Found">${dish.name}</h5>
                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item">${price}:${dish.price}</li>
                                        <li class="list-group-item">${category}:${dish.category}</li>
                                        <c:if test='${sessionScope.get("user").role eq "manager"}'>
                                            <li class="list-group-item">Amount:${dish.amount}</li>
                                        </c:if>
                                    </ul>
                                    <c:if test='${sessionScope.get("user").role eq "customer" || sessionScope.get("user").role eq "manager"}'>
                                        <div class="col-sm-7">

                                            <a href="FrontController?command=make-an-order?dishName=${dish.name}">
                                                <button type="button" class="btn btn-default"
                                                        data-dismiss="modal">Положить в корзину
                                                </button>
                                            </a>
                                                <%--                                        <form name="replenishStock" class="form-horizontal" method="post"--%>
                                                <%--                                              action="FrontController">--%>
                                                <%--                                            <input type="hidden" name="command" value="make-an-order"/>--%>
                                                <%--                                            <div class="replenishStock">--%>
                                                <%--                                                <div class="form-group">--%>
                                                <%--                                                    <div class="col-sm-offset-3 col-sm-7 batton-submit batton-submit-put">--%>
                                                <%--                                                        <button type="submit" class="btn btn-primary">Put in bucket--%>
                                                <%--                                                        </button>--%>
                                                <%--                                                    </div>--%>
                                                <%--                                                </div>--%>
                                                <%--                                            </div>--%>
                                                <%--                                        </form>--%>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <div id="replenish-stock" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title">Replenish stock</h4>
                        </div>
                        <div class="modal-body text-center">
                            <form name="replenishStock" class="form-horizontal" method="post" action="FrontController">
                                <input type="hidden" name="command" value="replenish-stock"/>
                                <span id="dishAmount" class="red"></span>
                                <div class="form-group">
                                    <div class="col-sm-7">
                                        <%--                                        <label for="dishName"></label>--%>
                                        <%--                                        <input type="text" class="form-control" id="dishName" placeholder="${name}"--%>
                                        <%--                                               name="dishName" required>--%>

                                        <label>
                                            <select class="sortStat" name="dishName">
                                                <c:forEach items="${requestScope.menu}" var="dish">
                                                    <option value="${dish.name}">${dish.name}</option>
                                                </c:forEach>
                                            </select>
                                        </label>
                                    </div>
                                    <div class="col-sm-7">
                                        <label for="amount"></label>
                                        <input type="text" class="form-control" id="amount" placeholder="amount"
                                               name="amount" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-3 col-sm-7">
                                        <button type="submit" class="btn btn-primary">Replenish stock</button>
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
        </div>
    </div>
</div>
<c:import url="template/footer.jsp"/>
<script src="../../js/test.js"></script>

</body>
</html>
