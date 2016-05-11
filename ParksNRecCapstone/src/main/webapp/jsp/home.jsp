<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Parks'n'Rec</title>
        <meta name="viewport" content="width=device-width, initial-scale=1"
              <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Open+Sans:400,800,600,700,300">
        <link rel="stylesheet" type="text/css" href="http://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    </head>
    <!-- header -->
    <body>
        <div class="header">
            <div class="col-sm-12 logo">
                Parks'n'Rec
            </div>
        </div>
        <div class="navbar navbar-inverse navbar-fixed">
            <div class="container">
                <div class="navbar-header">
                    <!-- this is for mobile/small viewports -->
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/home">PnR</a>
                </div>

                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="${pageContext.request.contextPath}/home"><span class="ion-home"></span></a></li>
                        <li><a href="${pageContext.request.contextPath}/reviews">Reviews</a></li>
                        <li><a href="${pageContext.request.contextPath}/author">Author <span class="ion-person"></span></a></li>
                                <sec:authorize access="hasRole('ROLE_PERSON')">
                            <li><a href="${pageContext.request.contextPath}/post">Post</a></li>
                            </sec:authorize>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <li><a href="${pageContext.request.contextPath}/reviewsToApprove">For Approval</a></li>
                            </sec:authorize>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">

                        <sec:authorize access="hasRole('ROLE_PERSON')" var="isLoggedIn"/>
                        <c:choose>
                            <c:when test="${isLoggedIn}">
                                <li><a href="${pageContext.request.contextPath}/j_spring_security_logout">Log out</a></li>
                                </c:when>
                                <c:otherwise>
                                <li><a href="${pageContext.request.contextPath}/login">Sign in</a></li>
                                </c:otherwise>
                            </c:choose>
                    </ul>
                </div>
            </div>
        </div>

        <!-- content -->
        <div class="container">
            <div class="row">
                <div class="col-md-3">  
                    <div class="jumbotron text-center">
                        <s:url value="singleReview" var="review_url" >
                            <s:param name="postId" value="${reviews0.id}"/>
                        </s:url>
                        <h3>
                            <img src="img/boots at lake photo.jpg" alt="boots in front of mountain" class="img-responsive"/>
                            <a href="${review_url}">${reviews0.title}</a>
                        </h3>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="jumbotron welcome ">
                        <h3>
                            Welcome to Parks'n'Rec! I am dedicated to bringing you the best reviews of National Parks and campgrounds.
                        </h3>
                         <img src="img/bL61wi8M.png" alt="Austyn Hill" class="img-rounded center-block"/>
                    </div>
                </div>
                <div class="col-md-3">  
                    <div class="jumbotron text-center">
                        <s:url value="singleReview" var="review_url" >
                            <s:param name="postId" value="${reviews1.id}"/>
                        </s:url>
                        <h3>
                            <img src="img/outdoors6.jpg" alt="mountain view at dusk" class="img-responsive"/>
                            <a href="${review_url}">${reviews1.title}</a>
                        </h3>
                    </div>
                </div>
            </div>

            <div class="row recent-three">
                <div class="col-md-3 col-md-offset-3">  
                    <div class="jumbotron recent-three text-center">
                        <s:url value="singleReview" var="review_url" >
                            <s:param name="postId" value="${reviews2.id}"/>
                        </s:url>
                        <h3>
                            <img src="img/montana-outdoors.jpg" alt="montana river and trees" class="img-responsive"/>
                            <a href="${review_url}">${reviews2.title}</a>
                        </h3>
                    </div>
                </div>
                <div class="col-md-3">  
                    <div class="jumbotron recent-three text-center">
                        <s:url value="singleReview" var="review_url" >
                            <s:param name="postId" value="${reviews3.id}"/>
                        </s:url>
                        <h3>
                            <img src="img/lookingUpTrees.jpg" alt="looking up through tree line" class="img-responsive"/>
                            <a href="${review_url}">${reviews3.title}</a>
                        </h3>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script src="${pageContext.request.contextPath}/js/jquery-1.12.2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>

</html>



