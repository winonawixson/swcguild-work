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
                <div class="col-md-offset-1" align="center">
                    <h1>Interest Calculator</h1>

                    <c:if test="${badInput == true}">
                        Please go back and provide correct input.<br>
                    </c:if>

                    <a href="${pageContext.request.contextPath}/interestCalc">Calculate Again</a>

                    <c:forEach items="${annualInterestData}" var="annualInterestMap">
                        <h3>Year: ${annualInterestMap.key}</h3>
                        Beginning Principal: $<c:out value="${annualInterestMap.value.principalAtBeg}" /><br>
                        End Principal: $ <c:out value="${annualInterestMap.value.principalAtEnd}" /><br>
                        Interest: $<c:out value="${annualInterestMap.value.earnedYearlyInterest}" /><br><br>
                    </c:forEach>
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

