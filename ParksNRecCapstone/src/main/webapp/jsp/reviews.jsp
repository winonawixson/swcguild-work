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
                        <li><a href="${pageContext.request.contextPath}/home"><span class="ion-home"></span></a></li>
                        <li class="active"><a href="${pageContext.request.contextPath}/reviews">Reviews</a></li>
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
                <!-- section for posts -->
                <div class="col-md-9">

                    <!-- each post -->
                    <c:if test="${reviews.size() < 1}">
                        <h3>Sorry, there are no reviews available.</h3>
                    </c:if>
                    <c:forEach var="review" items="${reviews}">
                        <s:url value="edit" var="editPost_url">
                            <s:param name="postId" value="${review.id}"/>
                        </s:url>
                        <s:url value="delete" var="deletePost_url" >
                            <s:param name="postId" value="${review.id}"/>
                        </s:url>
                        <s:url value="singleReview" var="review_url" >
                            <s:param name="postId" value="${review.id}"/>
                        </s:url>
                        <div class="jumbotron">
                            <div class="row">
                                <div class="col-sm-9">
                                    <h2>
                                        <a href="${review_url}">${review.title}</a>
                                    </h2>
                                    <small>by ${review.author}</small>
                                </div>
                                <div class="col-sm-3">
                                    <p>
                                        ${review.dateTime}
                                    </p>
                                    <p>${review.category.category}</p>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-md-12">
                                    <p>
                                        ${review.postContent}
                                    </p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-11">
                                    <c:forEach var="tag" items="${review.tags}">

                                        ${tag.tag}&nbsp;
                                    </c:forEach>
                                </div>
                                <div class="col-md-1">
                                    <sec:authorize access="hasRole('ROLE_PERSON')" var="isLoggedIn">
                                    <a href="${editPost_url}">
                                            <span class="glyphicon glyphicon-pencil"></span>
                                    </a>&nbsp;
                                    <a href="${deletePost_url}">
                                            <span class="glyphicon glyphicon-remove"></span>
                                    </a>
                                    </sec:authorize>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <!--end of post-->
                </div>
                <!-- side bar-->
                <div class="col-md-3">
                    <div class="jumbotron">
                        <h4>
                            Categories
                        </h4>
                        <ul>
                            <c:forEach var="category" items="${categories}">
                                <s:url value="reviewsByCategory" var="category_url">
                                    <s:param name="categoryId" value="${category.iD}"/>
                                </s:url>
                                <li>
                                    <a href="${category_url}">${category.category}</a>
                                </li>
                            </c:forEach>
                        </ul>
                        <h4>
                            Tags
                        </h4>
                        <ul>
                            
                            <c:forEach var="tag" items="${tags}">
                                <s:url value="reviewsByTag" var="tag_url">
                                    <s:param name="tagId" value="${tag.iD}"/>
                                </s:url>
                                <li>
                                    <a href="${tag_url}">${tag.tag}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-1.12.2.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>

</html>



