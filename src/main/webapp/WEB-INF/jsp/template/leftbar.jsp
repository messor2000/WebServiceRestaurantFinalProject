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

<div class="col-sm-2 sidenav">
    <c:if test='${sessionScope.get("user").role eq "manager"}'>
        <p><a class="manager" href="FrontController?command=show-all-users">Show all users</a></p>
<%--        <p><a class="manager" href="FrontController?command=add-dish">Add new dish</a></p>--%>
        <p><a data-toggle="modal" data-target="#addDish" href="#">
            <span class="glyphicon glyphicon"></span>Add new dish</a></p>
        <p><a class="manager" href="FrontController?command=replenish-stock">Replenish Stock</a></p>
    </c:if>
    <c:if test='${sessionScope.get("user").role eq "manager" || sessionScope.get("user").role eq "customer"}'>
<%--        <p><a class="manager" href="FrontController?command=show-purse">Show your purse</a></p>--%>
        <p><a class="manager" href="FrontController?command=show-purse&appUserId=${sessionScope.get('user').idUser}">Show your purse</a></p>

<%--    <a href="FrontController?command=show-purse&appUserId=${sessionScope.get('user').idUser}">--%>
    </c:if>

    <div id="addDish" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;  </button>
                    <h4 class="modal-title">Adding dish</h4>
                </div>
                <div class="modal-body text-center">
                    <form name="addingDish" class="form-horizontal" method="post" action="FrontController" onsubmit="return validateForm();">
                        <input type="hidden" name="command" value="add-dish"/>
                        <span id="dishName" class="red"></span>
                        <div class="form-group">
                            <label for="name" class="col-sm-3 control-label">Dish name</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" id="name" placeholder="name"
                                       name="name" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="price" class="col-sm-3 control-label">Dish price</label>
                            <span id="price" class="red"></span>
                            <div class="col-sm-7">
                                <label for="dishPrice"></label>
                                <input type="text" class="form-control" id="dishPrice" placeholder="Dish price"
                                                                      name="price" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="category" class="col-sm-3 control-label">Dish category</label>
                            <span id="category" class="red"></span>
                            <div class="col-sm-7">
                                <label for="dishCategory"></label>
                                <input type="text" class="form-control" id="dishCategory" placeholder="Dish category"
                                                                         name="category" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="amount" class="col-sm-3 control-label">Dish amount</label>
                            <span id="amount" class="red"></span>
                            <div class="col-sm-7">
                                <label for="dishAmount"></label>
                                <input type="text" class="form-control" id="dishAmount" placeholder="Dish amount"
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

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">${cancel}</button>
                </div>
            </div>
        </div>
    </div>
</div>
