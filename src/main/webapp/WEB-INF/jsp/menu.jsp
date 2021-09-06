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

                <c:if test='${sessionScope.get("user").role eq "customer" || sessionScope.get("user").role eq "manager"}'>
                    <c:forEach items="${requestScope.menu}" var="dish">
                        <div class="col-sm-6">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">${dish.name}</h5>
                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item">${dish.price}</li>
                                        <li class="list-group-item">${dish.category}</li>
                                    </ul>
                                    <a href="#" class="btn btn-primary">Put in bucket</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
                <c:forEach items="${requestScope.menu}" var="dish">
                    <div class="col-sm-6">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">${dish.name}</h5>
                                <ul class="list-group list-group-flush">
                                    <li class="list-group-item">${dish.price}</li>
                                    <li class="list-group-item">${dish.category}</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </c:forEach>

                <%--            <div class="selectboxes">--%>
                <%--                <div class="sortAwaits" style="width: 391px; float: left; margin: 0">--%>
                <%--                    <span>${sort}</span>--%>
                <%--                    <label>--%>
                <%--                        <select onchange="MakeSort(this);">--%>
                <%--                            <option value="FrontController?command=show-menu">-</option>--%>
                <%--                            <option selected="" value="FrontController?command=show-menu">${allMenu}</option>--%>
                <%--                            <option value="FrontController?command=movies-by-genre&genre=crime">${name}</option>--%>
                <%--                            <option value="FrontController?command=movies-by-genre&genre=adventure">${highPrice}</option>--%>
                <%--                            <option value="FrontController?command=movies-by-genre&genre=biography">${lowPrice}</option>--%>
                <%--                            <option value="FrontController?command=movies-by-genre&genre=thriller">${fastFood}</option>--%>
                <%--                            <option value="FrontController?command=movies-by-genre&genre=comedy">${healthyFood}</option>--%>
                <%--                            <option value="FrontController?command=movies-by-genre&genre=drama">${desert}</option>--%>
                <%--                        </select>--%>
                <%--                    </label>--%>
                <%--                    <br>--%>
                <%--                    <span>${byDecades}</span>--%>
                <%--                    <label>--%>
                <%--                        <select class="sortStat" onchange="MakeSort(this);">--%>
                <%--                            <option value="Controller?command=all-movies">-</option>--%>
                <%--                            <option value="Controller?command=movies-of-ten-year-period&years=1980">${eightieth}</option>--%>
                <%--                            <option value="Controller?command=movies-of-ten-year-period&years=1990">${ninetieth}</option>--%>
                <%--                            <option value="Controller?command=movies-of-ten-year-period&years=2000">${thousandth}</option>--%>
                <%--                            <option value="Controller?command=movies-of-ten-year-period&years=2010">${tenth}</option>--%>
                <%--                        </select>--%>
                <%--                    </label>--%>
                <%--                    <br>--%>
                <%--                    <span>${byYear}</span>--%>
                <%--                    <label>--%>
                <%--                        <select class="sortStat" name="y" onchange="MakeSort(this);">--%>
                <%--                            <option value="Controller?command=all-movies">-</option>--%>
                <%--                            <option value="Controller?command=movies-of-year&year=2016">2016</option>--%>
                <%--                            <option value="Controller?command=movies-of-year&year=2015">2015</option>--%>
                <%--                            <option value="Controller?command=movies-of-year&year=2014">2014</option>--%>
                <%--                            <option value="Controller?command=movies-of-year&year=2013">2013</option>--%>
                <%--                            <option value="Controller?command=movies-of-year&year=2012">2012</option>--%>
                <%--                            <option value="Controller?command=movies-of-year&year=2011">2011</option>--%>
                <%--                            <option value="Controller?command=movies-of-year&year=2010">2010</option>--%>
                <%--                            <option value="Controller?command=movies-of-year&year=2009">2009</option>--%>
                <%--                            <option value="Controller?command=movies-of-year&year=2008">2008</option>--%>
                <%--                            <option value="Controller?command=movies-of-year&year=2007">2007</option>--%>
                <%--                            <option value="Controller?command=movies-of-year&year=2006">2006</option>--%>
                <%--                            <option value="Controller?command=movies-of-year&year=2005">2005</option>--%>
                <%--                            <option value="Controller?command=movies-of-year&year=2004">2004</option>--%>
                <%--                            <option value="Controller?command=movies-of-year&year=2003">2003</option>--%>
                <%--                            <option value="Controller?command=movies-of-year&year=2002">2002</option>--%>
                <%--                            <option value="Controller?command=movies-of-year&year=2001">2001</option>--%>
                <%--                            <option value="Controller?command=movies-of-year&year=2000">2000</option>--%>
                <%--                            <option value="Controller?command=movies-of-year&year=1999">1999</option>--%>
                <%--                            <option value="Controller?command=movies-of-year&year=1998">1998</option>--%>
                <%--                            <option value="Controller?command=movies-of-year&year=1997">1997</option>--%>
                <%--                            <option value="Controller?command=movies-of-year&year=1996">1996</option>--%>
                <%--                            <option value="Controller?command=movies-of-year&year=1995">1995</option>--%>
                <%--                            <option value="Controller?command=movies-of-year&year=1994">1994</option>--%>
                <%--                            <option value="Controller?command=movies-of-year&year=1993">1993</option>--%>
                <%--                            <option value="Controller?command=movies-of-year&year=1992">1992</option>--%>
                <%--                            <option value="Controller?command=movies-of-year&year=1991">1991</option>--%>
                <%--                            <option value="Controller?command=movies-of-year&year=1990">1990</option>--%>
                <%--                            <option value="Controller?command=movies-of-year&year=1989">1989</option>--%>
                <%--                            <option value="Controller?command=movies-of-year&year=1988">1988</option>--%>
                <%--                            <option value="Controller?command=movies-of-year&year=1987">1987</option>--%>
                <%--                            <option value="Controller?command=movies-of-year&year=1986">1986</option>--%>
                <%--                            <option value="Controller?command=movies-of-year&year=1985">1985</option>--%>
                <%--                            <option value="Controller?command=movies-of-year&year=1984">1984</option>--%>
                <%--                            <option value="Controller?command=movies-of-year&year=1983">1983</option>--%>
                <%--                            <option value="Controller?command=movies-of-year&year=1982">1982</option>--%>
                <%--                            <option value="Controller?command=movies-of-year&year=1981">1981</option>--%>
                <%--                            <option value="Controller?command=movies-of-year&year=1980">1980</option>--%>
                <%--                        </select>--%>
                <%--                    </label>--%>
                <%--                    <br>--%>
                <%--                </div>--%>
                <%--                <div class="clear"></div>--%>
                <%--            </div>--%>
                <%--            <script type="text/javascript">--%>
                <%--                function MakeSort(element) {--%>
                <%--                    const selected = $('option:selected', element),--%>
                <%--                        href = selected.val();--%>
                <%--                    if (/*selected.text() === '-' || */!href) {--%>
                <%--                        return false;--%>
                <%--                    }--%>
                <%--                    document.location = href;--%>
                <%--                }--%>
                <%--            </script>--%>
                <%--            <br>--%>

                <%--            <table class="table table-striped">--%>
                <%--                <thead>--%>
                <%--                <tr>--%>
                <%--                    <th>${menu}</th>--%>
                <%--                    &lt;%&ndash;                    <th>${yourRating}</th>&ndash;%&gt;--%>
                <%--                    &lt;%&ndash;                    <th>${ratingVotes}</th>&ndash;%&gt;--%>
                <%--                </tr>--%>
                <%--                </thead>--%>
                <%--                <tbody>--%>
                <%--                <c:forEach var="menu" items="${requestScope.menu}">--%>
                <%--                    <tr>--%>
                <%--                            &lt;%&ndash;                        <c:if test="${sessionScope.get('language') eq 'ru' || sessionScope.get('language')==null}">&ndash;%&gt;--%>
                <%--                            &lt;%&ndash;                            <td><a href="FrontController?command=movie-by-id&id=${movie.id}">&ndash;%&gt;--%>
                <%--                            &lt;%&ndash;                                <c:out value="${movie.titleRu}"/></a></td>&ndash;%&gt;--%>
                <%--                            &lt;%&ndash;                        </c:if>&ndash;%&gt;--%>
                <%--                            &lt;%&ndash;                        <c:if test="${sessionScope.get('language') eq 'en'}">&ndash;%&gt;--%>
                <%--                            &lt;%&ndash;                            <td><a href="FrontController?command=movie-by-id&id=${movie.id}">&ndash;%&gt;--%>
                <%--                            &lt;%&ndash;                                <c:out value="${movie.titleEn}"/></a></td>&ndash;%&gt;--%>
                <%--                            &lt;%&ndash;                        </c:if>&ndash;%&gt;--%>
                <%--                        <td><a href="FrontController?command=show-menu"></a></td>--%>
                <%--                            &lt;%&ndash;                        <td>&ndash;%&gt;--%>
                <%--                            &lt;%&ndash;                            <c:forEach var="rating" items="${movie.ratings}">&ndash;%&gt;--%>
                <%--                            &lt;%&ndash;                                <c:if test="${rating.userNickname eq sessionScope.get('user').nickname}">&ndash;%&gt;--%>
                <%--                            &lt;%&ndash;                                    <c:out value="${rating.ratingScore}"/>&ndash;%&gt;--%>
                <%--                            &lt;%&ndash;                                </c:if>&ndash;%&gt;--%>
                <%--                            &lt;%&ndash;                            </c:forEach>&ndash;%&gt;--%>
                <%--                            &lt;%&ndash;                        </td>&ndash;%&gt;--%>
                <%--                            &lt;%&ndash;                        <td><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${movie.avgRating}" />&ndash;%&gt;--%>
                <%--                            &lt;%&ndash;                            <small>(<c:out value="${movie.ratingVotes}"/>)</small>&ndash;%&gt;--%>
                <%--                            &lt;%&ndash;                        </td>&ndash;%&gt;--%>
                <%--                    </tr>--%>
                <%--                </c:forEach>--%>
                <%--                </tbody>--%>
                <%--            </table>--%>
                <%--            <br>--%>


                <%--            &lt;%&ndash;For displaying Previous link except for the 1st page &ndash;%&gt;--%>
                <%--            <ul class="pagination">--%>
                <%--                <c:if test="${requestScope.currentPage > 1}">--%>
                <%--                    <li>--%>
                <%--                        <a href="FrontController?command=${param.command}&page=${requestScope.currentPage - 1}&country=${param.country}&genre=${param.genre}">${previous}</a>--%>
                <%--                    </li>--%>
                <%--                </c:if>--%>

                <%--                &lt;%&ndash;For displaying Page numbers.--%>
                <%--                The when condition does not display a link for the current page&ndash;%&gt;--%>
                <%--                <c:if test="${requestScope.noOfPages>1}">--%>
                <%--                    <c:forEach begin="1" end="${requestScope.noOfPages}" var="i">--%>
                <%--                        <c:choose>--%>
                <%--                            <c:when test="${requestScope.currentPage eq i}">--%>
                <%--                                <li class="active"><a href="#">${i}</a></li>--%>
                <%--                            </c:when>--%>
                <%--                            <c:otherwise>--%>
                <%--                                <li>--%>
                <%--                                    <a href="FrontController?command=${param.command}&page=${i}&country=${param.country}&genre=${param.genre}">${i}</a>--%>
                <%--                                </li>--%>
                <%--                            </c:otherwise>--%>
                <%--                        </c:choose>--%>
                <%--                    </c:forEach>--%>
                <%--                </c:if>--%>

                <%--                &lt;%&ndash;For displaying Next link &ndash;%&gt;--%>
                <%--                <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">--%>
                <%--                    <li>--%>
                <%--                        <a href="FrontController?command=${param.command}&page=${requestScope.currentPage + 1}&country=${param.country}&genre=${param.genre}">${next}</a>--%>
                <%--                    </li>--%>
                <%--                </c:if>--%>
                <%--            </ul>--%>
            </div>

            <%--        <c:import url="template/sideright.jsp"/>--%>

        </div>
    </div>
    <c:import url="template/footer.jsp"/>
</body>
</html>
