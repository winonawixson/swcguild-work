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

                    <form method="POST" action="interestCalc">
                        What is the annual interest rate for this mutual fund:
                        <input type="number" min="0" name="annualInterestRate" /> <br><br>

                        How much would you like to invest: 
                        <input type="number" min="0" name="initialPrincipal" /> <br><br>

                        How many years would you like to invest in our fund: 
                        <input type="number" min="0" name="years" /> <br><br>

                        <button type="submit" value="Submit">Submit</button>        
                    </form>
                    
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

