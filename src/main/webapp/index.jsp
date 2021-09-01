<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set scope="session" var="previousQuery" value="index.jsp"/>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.indexTitle" var="indexTitle"/>
<fmt:message bundle="${locale}" key="locale.searchTitle" var="searchTitle"/>
<fmt:message bundle="${locale}" key="locale.searchLabel" var="searchLabel"/>
<fmt:message bundle="${locale}" key="locale.search" var="search"/>
<fmt:message bundle="${locale}" key="locale.movie" var="movie"/>
<!DOCTYPE html>
<html>
<head>
    <title>${indexTitle}</title>
    <link rel="shortcut icon" href="images/main/favicon_16x16.png">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="src/first.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!--<script src="js/bootstrap.min.js"></script>
    <script src="js/docs.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="js/ie10-viewport-bug-workaround.js"></script>

    <!-- jssor slider scripts-->
    <!-- use jssor.slider.debug.js for debug -->
    <script type="text/javascript" src="js/jssor.slider.mini.js"></script>

    <style>
        /* jssor slider bullet navigator skin 05 css */
        /*
        .jssorb05 div           (normal)
        .jssorb05 div:hover     (normal mouseover)
        .jssorb05 .av           (active)
        .jssorb05 .av:hover     (active mouseover)
        .jssorb05 .dn           (mousedown)
        */
        .jssorb05 {
            position: absolute;
        }
        .jssorb05 div, .jssorb05 div:hover, .jssorb05 .av {
            position: absolute;
            /* size of bullet elment */
            width: 16px;
            height: 16px;
            background: url(images/main/b05.png) no-repeat;
            overflow: hidden;
            cursor: pointer;
        }
        .jssorb05 div {
            background-position: -7px -7px;
        }
        .jssorb05 div:hover, .jssorb05 .av:hover {
            background-position: -37px -7px;
        }
        .jssorb05 .av {
            background-position: -67px -7px;
        }
        .jssorb05 .dn, .jssorb05 .dn:hover {
            background-position: -97px -7px;
        }
    </style>
    <style>
        /* jssor slider arrow navigator skin 11 css */
        /*
        .jssora11l                  (normal)
        .jssora11r                  (normal)
        .jssora11l:hover            (normal mouseover)
        .jssora11r:hover            (normal mouseover)
        .jssora11l.jssora11ldn      (mousedown)
        .jssora11r.jssora11rdn      (mousedown)
        */
        .jssora11l, .jssora11r {
            display: block;
            position: absolute;
            /* size of arrow element */
            width: 37px;
            height: 37px;
            cursor: pointer;
            background: url(images/main/a11.png) no-repeat;
            overflow: hidden;
        }
        .jssora11l {
            background-position: -11px -41px;
        }
        .jssora11r {
            background-position: -71px -41px;
        }
        .jssora11l:hover {
            background-position: -131px -41px;
        }
        .jssora11r:hover {
            background-position: -191px -41px;
        }
        .jssora11l.jssora11ldn {
            background-position: -251px -41px;
        }
        .jssora11r.jssora11rdn {
            background-position: -311px -41px;
        }
    </style>
</head>
<body onload="active()">

<c:import url="WEB-INF/jsp/template/navbar.jsp"/>
<script language="javascript">
    function active() {
        document.getElementById("index-page").className = "active";
    }
</script>
<div class="container-fluid text-center wrapper">
    <div class="row content">

        <c:import url="WEB-INF/jsp/template/sideleft.jsp"/>

        <div class="col-sm-8 text-left mainContent">
            <br>
            <form method="get" action="Controller" class="form-horizontal">
                <input type="hidden" name="command" value="find-movie-by-title"/>
                <div class="form-group">
                    <label for="search" class="col-sm-2 control-label">${searchLabel}</label>
                    <div class="col-sm-8">
                        <input id="search" title="${searchTitle}" type="text" class="form-control"
                               placeholder="${movie}"
                               name="movieTitle"/>
                        <br/>
                    </div>
                    <div class="col-sm-2">
                        <button type="submit" class="btn btn-success">${search}</button>
                    </div>
                </div>

            </form>
            <hr>
            <div id="slider1_container"
                 style="visibility: hidden; position: relative; margin: 0 auto; width: 1000px; height: 700px; overflow: hidden;">

                <!-- Loading Screen -->
                <div u="loading" style="position: absolute; top: 0px; left: 0px;">
                    <div style="filter: alpha(opacity=70); opacity:0.7; position: absolute; display: block;
                background-color: #000; top: 0px; left: 0px;width: 100%; height:100%;">
                    </div>
                    <div style="position: absolute; display: block; background: url(images/main/loading.gif) no-repeat center center;
                top: 0px; left: 0px;width: 100%;height:100%;">
                    </div>
                </div>

                <!-- Slides Container -->
                <div u="slides" style="position: absolute; left: 0px; top: 0px; width: 1000px; height: 700px;
            overflow: hidden;">
                    <div>
                        <a href="Controller?command=all-movies"><img u="image" src2="images/main/bestmovies.jpg"/></a>
                    </div>
                    <div>
                        <a href="Controller?command=latest-movies"><img u="image" src2="images/main/newmovies.jpg"/></a>
                    </div>
                    <div>
                        <a href="Controller?command=all-movies"><img u="image" src2="images/main/genres.jpg"/></a>
                    </div>
                    <div>
                        <a href="Controller?command=view-latest-reviews"><img u="image" src2="images/main/reviews.jpg"/></a>
                    </div>
                </div>

                <!--#region Bullet Navigator Skin Begin -->
                <!-- Help: http://www.jssor.com/tutorial/set-bullet-navigator.html -->

                <!-- bullet navigator container -->
                <div u="navigator" class="jssorb05" style="bottom: 16px; right: 6px;">
                    <!-- bullet navigator item prototype -->
                    <div u="prototype"></div>
                </div>
                <!--#endregion Bullet Navigator Skin End -->

                <!--#region Arrow Navigator Skin Begin -->
                <!-- Help: http://www.jssor.com/tutorial/set-arrow-navigator.html -->

                <!-- Arrow Left -->
                <span u="arrowleft" class="jssora11l" style="top: 123px; left: 8px;">
            </span>
                <!-- Arrow Right -->
                <span u="arrowright" class="jssora11r" style="top: 123px; right: 8px;">
            </span>
                <!--#endregion Arrow Navigator Skin End -->
                <a style="display: none" href="http://www.jssor.com">Bootstrap Carousel</a>
            </div>
            <!-- Jssor Slider End -->
            <hr>
        </div>

        <c:import url="WEB-INF/jsp/template/sideright.jsp"/>

    </div>
</div>