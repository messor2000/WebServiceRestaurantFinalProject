<%--
  Created by IntelliJ IDEA.
  User: olexandr
  Date: 02.09.2021
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set scope="session" var="previousQuery" value="index.jsp"/>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.indexTitle" var="indexTitle"/>
<!DOCTYPE html>
<html>
<head>
    <title>${indexTitle}</title>
    <%--    <link rel="shortcut icon" href="images/main/favicon_16x16.png">--%>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/first.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <%--    <script src="js/bootstrap.min.js"></script>--%>
    <%--        <script src="js/docs.min.js"></script>--%>
    <%--    IE10 viewport hack for Surface/desktop Windows 8 bug--%>
    <%--    <script src="js/ie10-viewport-bug-workaround.js"></script>--%>

    <%--    jssor slider scripts-->--%>
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

        <c:import url="WEB-INF/jsp/template/leftbar.jsp"/>

        <div class="col-sm-8 text-left mainContent">
                        <br>
                        <form method="get" action="FrontController" class="form-horizontal">
                            <input type="hidden" name="command" value="find-movie-by-title"/>
<%--                            <div class="form-group">--%>
<%--                                <label for="search" class="col-sm-2 control-label">${searchLabel}</label>--%>
<%--                                <div class="col-sm-8">--%>
<%--                                    <input id="search" title="${searchTitle}" type="text" class="form-control"--%>
<%--                                           placeholder="${movie}"--%>
<%--                                           name="movieTitle"/>--%>
<%--                                    <br/>--%>
<%--                                </div>--%>
<%--                                <div class="col-sm-2">--%>
<%--                                    <button type="submit" class="btn btn-success">${search}</button>--%>
<%--                                </div>--%>
<%--                            </div>--%>

                        </form>
                        <hr>
            <div id="slider1_container"
                 style="visibility: hidden; position: relative; margin: 0 auto; width: 1000px; height: 700px; overflow: hidden;">

                <!-- Loading Screen -->
                <%--                <div u="loading" style="position: absolute; top: 0px; left: 0px;">--%>
                <%--                    <div style="filter: alpha(opacity=70); opacity:0.7; position: absolute; display: block;--%>
                <%--                            background-color: #000; top: 0px; left: 0px;width: 100%; height:100%;">--%>
                <%--                    </div>--%>
                <%--                    <div style="position: absolute; display: block; background: url(images/main/loading.gif) no-repeat center center;--%>
                <%--                            top: 0px; left: 0px;width: 100%;height:100%;">--%>
                <%--                    </div>--%>
                <%--                </div>--%>

                <!-- Slides Container -->
                <div u="slides"
                     style="position: absolute; left: 0px; top: 0px; width: 1000px; height: 700px;overflow: hidden;">
                    <div>
                        <a href="FrontController?command=all-movies"><img u="image" src="image/coffee1.jpg"
                                                                          alt="coffee1"/></a>
                    </div>
                    <div>
                        <a href="FrontController?command=all-movies"><img u="image" src="image/coffee1.jpg"
                                                                          alt="coffee1"/></a>
                    </div>
                    <div>
                        <a href="FrontController?command=all-movies"><img u="image" src="image/coffee1.jpg"
                                                                          alt="coffee1"/></a>
                    </div>
                    <div>
                        <a href="FrontController?command=all-movies"><img u="image" src="image/coffee1.jpg"
                                                                          alt="coffee1"/></a>

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

        <%--        <c:import url="WEB-INF/jsp/template/sideright.jsp"/>--%>

    </div>
</div>

<c:import url="WEB-INF/jsp/template/footer.jsp"/>
<script>
    jQuery(document).ready(function ($) {
        const options = {
            $AutoPlay: true,                                    //[Optional] Whether to auto play, to enable slideshow, this option must be set to true, default value is false
            $AutoPlaySteps: 1,                                  //[Optional] Steps to go for each navigation request (this options applys only when slideshow disabled), the default value is 1
            $Idle: 2000,                                        //[Optional] Interval (in milliseconds) to go for next slide since the previous stopped if the slider is auto playing, default value is 3000
            $PauseOnHover: 1,                                   //[Optional] Whether to pause when mouse over if a slider is auto playing, 0 no pause, 1 pause for desktop, 2 pause for touch device, 3 pause for desktop and touch device, 4 freeze for desktop, 8 freeze for touch device, 12 freeze for desktop and touch device, default value is 1
            $ArrowKeyNavigation: true,   			            //[Optional] Allows keyboard (arrow key) navigation or not, default value is false
            $SlideEasing: $JssorEasing$.$EaseOutQuint,          //[Optional] Specifies easing for right to left animation, default value is $JssorEasing$.$EaseOutQuad
            $SlideDuration: 800,                                //[Optional] Specifies default duration (swipe) for slide in milliseconds, default value is 500
            $MinDragOffsetToSlide: 20,                          //[Optional] Minimum drag offset to trigger slide , default value is 20
            //$SlideWidth: 600,                                 //[Optional] Width of every slide in pixels, default value is width of 'slides' container
            //$SlideHeight: 300,                                //[Optional] Height of every slide in pixels, default value is height of 'slides' container
            $SlideSpacing: 0, 					                //[Optional] Space between each slide in pixels, default value is 0
            $Cols: 1,                                  //[Optional] Number of pieces to display (the slideshow would be disabled if the value is set to greater than 1), the default value is 1
            $ParkingPosition: 0,                                //[Optional] The offset position to park slide (this options applys only when slideshow disabled), default value is 0.
            $UISearchMode: 1,                                   //[Optional] The way (0 parellel, 1 recursive, default value is 1) to search UI components (slides container, loading screen, navigator container, arrow navigator container, thumbnail navigator container etc).
            $PlayOrientation: 1,                                //[Optional] Orientation to play slide (for auto play, navigation), 1 horizental, 2 vertical, 5 horizental reverse, 6 vertical reverse, default value is 1
            $DragOrientation: 1,                                //[Optional] Orientation to drag slide, 0 no drag, 1 horizental, 2 vertical, 3 either, default value is 1 (Note that the $DragOrientation should be the same as $PlayOrientation when $Cols is greater than 1, or parking position is not 0)
            $ArrowNavigatorOptions: {                           //[Optional] Options to specify and enable arrow navigator or not
                $Class: $JssorArrowNavigator$,                  //[Requried] Class to create arrow navigator instance
                $ChanceToShow: 2,                               //[Required] 0 Never, 1 Mouse Over, 2 Always
                $AutoCenter: 2,                                 //[Optional] Auto center arrows in parent container, 0 No, 1 Horizontal, 2 Vertical, 3 Both, default value is 0
                $Steps: 1,                                      //[Optional] Steps to go for each navigation request, default value is 1
                $Scale: false                                   //Scales bullets navigator or not while slider scale
            },
            $BulletNavigatorOptions: {                                //[Optional] Options to specify and enable navigator or not
                $Class: $JssorBulletNavigator$,                       //[Required] Class to create navigator instance
                $ChanceToShow: 2,                               //[Required] 0 Never, 1 Mouse Over, 2 Always
                $AutoCenter: 1,                                 //[Optional] Auto center navigator in parent container, 0 None, 1 Horizontal, 2 Vertical, 3 Both, default value is 0
                $Steps: 1,                                      //[Optional] Steps to go for each navigation request, default value is 1
                $Rows: 1,                                      //[Optional] Specify lanes to arrange items, default value is 1
                $SpacingX: 12,                                   //[Optional] Horizontal space between each item in pixel, default value is 0
                $SpacingY: 4,                                   //[Optional] Vertical space between each item in pixel, default value is 0
                $Orientation: 1,                                //[Optional] The orientation of the navigator, 1 horizontal, 2 vertical, default value is 1
                $Scale: false                                   //Scales bullets navigator or not while slider scale
            }
        };
        const jssor_slider1 = new $JssorSlider$("slider1_container", options);
        //responsive code begin
        //you can remove responsive code if you don't want the slider scales while window resizing
        function ScaleSlider() {
            const parentWidth = jssor_slider1.$Elmt.parentNode.clientWidth;
            if (parentWidth) {
                jssor_slider1.$ScaleWidth(parentWidth - 30);
            } else
                window.setTimeout(ScaleSlider, 30);
        }

        ScaleSlider();
        $(window).bind("load", ScaleSlider);
        $(window).bind("resize", ScaleSlider);
        $(window).bind("orientationchange", ScaleSlider);
        //responsive code end
    });
</script>
</body>
</html>
