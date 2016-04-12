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
                <div class="col-md-offset-3" align="left">
                    <h2>Unit Converter</h2>

                    <div class="col-md-8">
                        <div class="row">

                            Original Value: <c:out value="${converter.originalValue}" />
                            <br>
                            Converted Value: <c:out value="${converter.convertedValue}" />

                        </div>
                        <c:if test="badInput == true">
                            <br> Bad Input....
                        </c:if>
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

