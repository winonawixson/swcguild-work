<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Vending Machine</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body>
        <div class="container">

            <h1 align="center">Vending Machine</h1>
            <hr/>

            <div class="row col-md-offset-1 col-md-10">
                <div class="col-md-9">
                    <h3>Inventory</h3>
                    <div id="displayItemsDiv">
                      
                    </div>

                </div>
                <div class="col-md-3">
                    <div id="purchaseResults">
                       
                    </div>

                    <h3>Payment Console</h3>
                    <form class="form-vertical" role="form" method="POST">
                        <div class="form-group">
                            <label for="itemNumber" class="control-label">Item Number to Purchase:</label><br/>
                            <div><input type="number" min="0" class="form-control" id="itemNumber" name="itemNumber" placeholder="Item to Purchase" /></div>
                        </div>
                        <div class="form-group">
                            <label for="userMoney" class="control-label">Insert Money:</label><br/>
                            <div><input type="number" min="0" step="0.01" class="form-control" id="userMoney" name="userMoney" placeholder="Insert Money" /></div>
                        </div>
                        <div id="badInputSection" style="color: red">
                            
                        </div>
                        <div class="form-group">
                            <div class="col-md-4">
                                <button id="buy-button" type="submit" class="btn btn-default btn-primary">Purchase</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <!-- Placed at the end of the document so the pages load faster -->
            <script src="${pageContext.request.contextPath}/js/jquery-1.12.2.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/itemList.js"></script>
    </body>
</html>

