<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Address Book</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        
        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body>
        <div class="container">
            <h1>Address Book</h1>
            <hr/>
            <div class="navbar">
                 <ul class="nav nav-tabs">
                 <li role="presentation"><a href="${pageContext.request.contextPath}">Home</a></li>
                <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/search">Search</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/stats">Stats</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/displaySearchFormWithoutAjax">Search Form (no ajax)</a></li>
                </ul>    
            </div>
            <h2>Search Page</h2>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.12.2.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

