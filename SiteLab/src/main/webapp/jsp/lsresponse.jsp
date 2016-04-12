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
                    <h2>Lucky Sevens</h2>
                    <c:choose>
                        <c:when test="${badInput}">
                            Please enter a valid starting bet.<br>
                            <form action="luckySevensApp" class="form-group" method="POST">
                                Starting Bet:
                                <input type="number" class="form-control" min="1" id="startBet" name="startBet" width="75%" placeholder="$0.00"> 
                                <br>
                                <div>
                                    <input type="submit" class="btn" value="Play" />
                                </div>
                            </form>

                        </c:when>
                        <c:otherwise>
                            <div class="row">
                                <div class="col-md-3"
                                     <form action="luckySevensApp" class="form-group" method="POST">
                                        Starting Bet:
                                        <input type="number" class="form-control" min="1" id="startBet" name="startBet" width="75%" placeholder="$0.00"> 
                                        <br>
                                        <div>
                                            <input type="submit" class="btn" value="Play" />
                                        </div>
                                    </form>
                                </div>

                                <div id="results" class="col-md-offset-1 col-md-6">
                                    <div class="jumbotron" >
                                        <h3>Results</h3>
                                        Starting Bet: $<c:out value="${gameStats.bankroll}" default="Unknown"/>.00<br><br>
                                        Total Rolls Before Going Broke: <c:out value="${gameStats.rollCount}" default="Unknown"/><br><br>
                                        Highest Amount Won: $<c:out value="${gameStats.maxBankroll}" default="Unknown"/>.00<br><br>
                                        Roll Count at Highest Amount Won: <c:out value="${gameStats.rollCountAtMax}" default="Unknown"/>

                                    </div>
                                </div> 

                            </div>
                        </c:otherwise>
                    </c:choose>
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

