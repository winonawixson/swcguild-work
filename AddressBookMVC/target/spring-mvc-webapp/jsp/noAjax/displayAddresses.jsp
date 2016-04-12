<%-- 
    Document   : withoutAjaxDisplayAddresses
    Created on : Apr 1, 2016, 8:47:13 AM
    Author     : Winona Wixson 
--%>

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
                    <li role="presentation"><a href="${pageContext.request.contextPath}/search">Search</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/stats">Stats</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/displayAddressBookNoAjax">Display Addresses (no ajax)</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/newAddressNoAjax">Add Address (no ajax)</a></li>
                </ul>    
            </div>
            <h2>Addresses</h2>
            
            <c:if test="${fromSearch}">
                Here are you search results:<br><br>
                <c:if test="${listSize < 1}">
                    Nothing matches your search. Please search again...<br>
                </c:if>
            </c:if>
            
            <c:forEach items="${addressList}" var="address">
                
                <s:url value="deleteAddressNoAjax" var="deleteAddress_url">
                    <s:param name="aId" value="${address.addressId}" />
                </s:url>
                
                <s:url value="editAddressNoAjax" var="editAddress_url">
                    <s:param name="aId" value="${address.addressId}" />
                </s:url>
                
                ${address.firstName} ${address.lastName} | <a href="${deleteAddress_url}">Delete </a>| <a href="${editAddress_url}">Edit </a><br>
                ${address.streetAddress}<br>
                ${address.city}, ${address.state} ${address.zip}<br><br>
                
                
            </c:forEach>
            
            
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.12.2.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
