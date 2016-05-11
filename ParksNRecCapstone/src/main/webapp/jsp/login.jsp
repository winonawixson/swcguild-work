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
                        <li><a href="${pageContext.request.contextPath}/reviews">Reviews</a></li>
                        <li><a href="${pageContext.request.contextPath}/author">Author <span class="ion-person"></span></a></li>
                        <sec:authorize access="hasRole('ROLE_PERSON')">
                            <li><a href="${pageContext.request.contextPath}/post">Post</a></li>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <li><a href="${pageContext.request.contextPath}/reviewsToApprove">For Approval</a></li>
                        </sec:authorize>
                    </ul>
<!--                    <ul class="nav navbar-nav navbar-right">
                    <sec:authorize access="hasRole('ROLE_PERSON')" var="isLoggedIn">
                    <c:choose>
                        <c:when test="${isLoggedIn}">
                            <li><a href="${pageContext.request.contextPath}/j_spring_security_logout">Log out</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="${pageContext.request.contextPath}/login">Sign in</a></li>
                        </c:otherwise>
                    </c:choose>
                    </sec:authorize>
                    </ul>-->
                </div>
            </div>
        </div>

        <!-- content -->
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-4">

                    
                    <!-- #1 - If login_error == 1 then there was a failed login attempt --> 
                    <!-- so display an error message --> 
                    <c:if test="${param.login_error == 1}">
                        <h3>Wrong id or password!</h3> 
                    </c:if>
                    <!-- #2 - Post to Spring security to check our authentication -->
                    <div class="jumbotron">
                        <!--form sign in-->
                        <form class="form-signin" action="j_spring_security_check" method="POST">
                            <h2 class="form-signin-heading">Please sign in</h2>
                                
                            <label for="username" class="sr-only">Username</label>
                                <input type="text" id="username" class="form-control" name="j_username" placeholder="Username" required autofocus>
                            <label for="password" class="sr-only">Password</label>
                                <input type="password" name="j_password" id="password" class="form-control" placeholder="Password" required>
<!--                        <div class="checkbox">
                                <label>
                                <input type="checkbox" value="remember-me"> Remember me
                                </label>
                            </div>-->
                            <button name="commit" class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
                        </form>
<!--                        <form method="post" class="signin" action="j_spring_security_check"> <fieldset>
                                <table cellspacing="0"> <tr>
                                        <th>
                                            <label for="username">Username </label>
                                        </th>
                                        <td><input id="username_or_email" 
                                                   name="j_username"
                                                   type="text" />
                                        </td> </tr>
                                    <tr>
                                        <th><label for="password">Password</label></th> 
                                         #2b - must be j_password for Spring  
                                        <td><input id="password"
                                                   name="j_password"
                                                   type="password" />
                                        </td> 
                                    </tr>
                                    <tr> 
                                        <th></th>
                                        <td><input name="commit" type="submit" value="Sign In" /></td> </tr>
                                </table> </fieldset>
                        </form> -->
                    </div>
                </div>
            </div>
        </div>


        <script src="${pageContext.request.contextPath}/js/jquery-1.12.2.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>

</html>






<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html> <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contact List</title> </head>
    <body> 

    </body> 
</html>