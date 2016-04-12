<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Site Lab - Home Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/jsp/siteStyling.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body id="body">
        <!--Page Header-->
        <div class="container">
            <%@include file="header.jsp" %>

            <!--Page Content-->
            <div style="color: #0f0f0f">
                <div class="row col-md-offset-1">
                    <div class="col-md-5 jumbotron jumbotron">
                        <img class="img-responsive" src="${pageContext.request.contextPath}/img/winterpic.png" alt="winter pic" style="width: 400px; height: 250px"/>
                    </div>
                    <div class="col-md-offset-1 col-md-5 jumbotron">
                        <img class="img-responsive" src="${pageContext.request.contextPath}/img/family.png" alt="family pic" style="width: 400px; height: 250px"/>
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-md-offset-4">
                        <img class="img-responsive jumbotron" src="${pageContext.request.contextPath}/img/sunset.png" alt="sunset pic" style="width: 400px; height: 300px"/>
                    </div>
                </div>
            <!--</div>-->
            <hr/>

            <!--Page Footer-->
            <%@include file="footer.jsp" %>

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.12.2.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

