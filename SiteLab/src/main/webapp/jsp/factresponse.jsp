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
        <div class="container">
            <%@include file="header.jsp" %>
                    
            <div class="row" style="color: #0f0f0f">
                <div class="col-md-offset-3">
                    <h2>Welcome to the Factorizor</h2>

                    Number to factor: <c:out value="${factorizations.num}" default="n/a" />
                    <br><br>

                    Factors: <c:forEach items="${factorizations.factors}" var="factor" >
                        <c:out value="${factor}" />
                    </c:forEach>
                    <br><br>

                    Prime: <c:if test="${factorizations.prime == true}"> yes!</c:if>
                    <c:if test="${factorizations.prime == false}"> no...</c:if>
                    <br><br>

                        Perfect: <c:if test="${factorizations.perfect == true}"> yes!</c:if>
                    <c:if test="${factorizations.perfect == false}"> no...</c:if>
                        <br><br>
                    </div>
                </div>

                <hr/>

               <%@include file="footer.jsp" %>

            </div>
            <!-- Placed at the end of the document so the pages load faster -->
            <script src="${pageContext.request.contextPath}/js/jquery-1.12.2.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

