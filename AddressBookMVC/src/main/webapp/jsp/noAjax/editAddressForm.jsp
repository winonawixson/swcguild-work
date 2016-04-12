<%-- 
    Document   : withoutAjaxDisplayAddresses
    Created on : Apr 1, 2016, 8:47:13 AM
    Author     : Winona Wixson 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
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
                    <li role="presentation"><a href="${pageContext.request.contextPath}/search">Search</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/stats">Stats</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayAddressBookNoAjax">Display Addresses (no ajax)</a></li>
                </ul>    
            </div>

            <sf:form class="form-horizontal" role="form" modelAttribute="addressToEdit"
                     action="editAddressNoAjax" method="POST">

                <div class="form-group">
                    <label for="edit-first-name" class="col-md-4 control-label">First Name:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="edit-first-name" path="firstName" placeHolder="First Name" />
                        <sf:errors path = "firstName" cssClass="error" cssStyle="color:red"></sf:errors>
                        </div>
                    </div> 

                    <div class="form-group">
                        <label for="edit-last-name" class="col-md-4 control-label">Last Name:</label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="edit-last-name" path="lastName" placeHolder="Last Name" />
                        <sf:errors path = "lastName" cssClass="error" cssStyle="color:red"></sf:errors>
                        </div>
                    </div> 

                    <div class="form-group">
                        <label for="edit-stAddress" class="col-md-4 control-label">Street Address:</label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="edit-stAddress" path="streetAddress" placeHolder="Street Address" />
                        <sf:errors path = "streetAddress" cssClass="error" cssStyle="color:red"></sf:errors>
                        </div>
                    </div> 

                    <div class="form-group">
                        <label for="edit-city" class="col-md-4 control-label">City:</label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="edit-city" path="city" placeHolder="City" />
                        <sf:errors path = "city" cssClass="error" cssStyle="color:red"></sf:errors>
                        </div>
                    </div> 

                    <div class="form-group">
                        <label for="edit-state" class="col-md-4 control-label">State: </label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="edit-state" path="state" placeHolder="State" />
                        <sf:errors path = "state" cssClass="error" cssStyle="color:red"></sf:errors>
                        </div>
                    </div> 

                    <div class="form-group">
                        <label for="edit-zip" class="col-md-4 control-label">Zip: </label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="edit-zip" path="zip" placeHolder="Zip" />
                        <sf:errors path = "zip" cssClass="error" cssStyle="color:red"></sf:errors>
                        </div>
                    </div> 

                    <div class="form-group">
                    <sf:hidden path="addressId" />
                    <div class="col-md-offset-4 col-md-8">
                        <button type="submit" id="edit-button" class="btn btn-info">Save Address</button>
                    </div>
                </div>
            </sf:form>



        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.12.2.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
