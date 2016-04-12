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
                    <div class="col-md-3">
                        <form method="POST" class="form-vertical" action="flooringCalculation">
                            Width(ft): <input type="number" class="form-control" min="0" max="1999" step="1" name ="width"><br /><br />
                            Length(ft): <input type="number" class="form-control" min="0" max="1999" step="1" name ="length"><br /><br />
                            Cost/SqFt: <input type="number" class="form-control" min="0" max="199" step="1" name ="costSqFt"><br /><br />
                            <button type="submit" class="btn" value="Submit">Submit</button> 

                        </form>
                    </div>
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

