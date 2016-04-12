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
                    <h2>Flooring Calculator</h2>

                    <c:if test="${badInput == true}">
                        Please go back and provide correct input.<br>
                        <a href="index.jsp">Return to Flooring form</a>
                    </c:if>
                    Area:<br>
                    <c:out value="${width}" default="n/a" />ft *
                    <c:out value="${length}" default="n/a" />ft =
                    <c:out value="${flooringArea}" default="n/a" />sqft <br><br>

                    Flooring Cost:<br>
                    <c:out value="${flooringArea}" default="n/a" />sqft * 
                    $<c:out value="${costSqFt}" default="n/a" />/sqft = 
                    $<c:out value="${flooringCost}" default="n/a" /><br><br>

                    Labor Time:<br>
                    <c:out value="${flooringArea}" default="n/a" />/ 20sqft/hr =
                    <c:out value="${laborTime}" default="n/a" /> 15m increments (rounded to next 15m increment)
                    <br><br>

                    Labor Cost: <br>
                    <c:out value="${laborTime}" default="n/a" /> 15m increments * $86/hr = 
                    $<c:out value="${laborCost}" default="n/a" />
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

