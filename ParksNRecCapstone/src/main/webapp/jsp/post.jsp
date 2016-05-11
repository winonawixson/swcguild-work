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
        <script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
        <script type="text/javascript">
            tinymce.init({
                selector: "textarea",
                plugins: [
                    "advlist autolink lists link image charmap print preview anchor",
                    "searchreplace visualblocks code fullscreen",
                    "insertdatetime media table contextmenu paste"
                ],
                toolbar: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image"
            });
        </script>
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
                <div class="col-md-8 col-md-offset-2">
                    <h3>Post a new Review</h3>
                    <form method="POST" action="addPost" role="form" class="form-vertical">
                        <div class="form-group">
                            <label for="title" class="col-md-12 control-label">Title:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="add-title" name="title" placeholder="Review title">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="author" class="col-md-12 control-label post-label">Author:</label>
                            <div class="col-md-8">
                                <input type="text" class="col-md-8 form-control" id="add-title" name="author" placeholder="Review author">
                            </div>
                        </div>
                        <div class="form-group" >
                            <label for="postContent" class="col-md-12 control-label post-label">Post:</label>
                        </div>

                        <div class="col-md-12">
                            <textarea name="postContent"></textarea>
                        </div>

                        <div class="form-group">
                            <label for="Category" class="col-md-12 control-label post-label">Category:</label>
                            <div class="col-md-4">
                                <input type="text" class="col-md-8 form-control" id="add-category" name="category" placeholder="Review category" required>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="Tag" class="col-md-12 control-label post-label">Tag:</label>
                            <div class="col-md-4">
                                <input type="text" class="col-md-8 form-control" id="add-tag" name="tag" placeholder="Review tag">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-12 post-label">
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <div class="checkbox">
                                    <label><input type="checkbox" name="approved" value="true">Approved</label>
                                </div>
                            </sec:authorize>
                                <button type="submit" class="btn btn-primary">Submit</button>
                            </div>

                        </div>


                    </form>
                </div>
            </div>
        </div>


        <script src="${pageContext.request.contextPath}/js/jquery-1.12.2.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>

</html>



