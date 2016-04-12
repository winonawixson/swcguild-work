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
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/search">Search</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/stats">Stats</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayAddressBookNoAjax">Display Addresses (no ajax)</a></li>
                </ul>    
            </div>

            <div class="row">
                <div class="col-md-5">
                    <h2>My Addresses</h2>
                    <table id="addressTable" class="table table-hover">
                        <tr>
                            <th width="40%">Name</th>
                            <th width="30%">Street Address</th>
                            <th width="15%"></th>
                            <th width="15%"></th>
                        </tr>
                        <tbody id="addressTableRows">

                        </tbody>

                    </table>
                </div>

                <div class="col-md-offset-1 col-md-6 jumbotron">
                    <h2>Add New Address</h2>

                    <form class="form-horizontal" role="form" method="POST">
                        <div class="form-group">
                            <label for="add-first-name" class="col-md-4 control-label">First Name:</label> 
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="add-first-name" placeholder="First Name"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="add-last-name" class="col-md-4 control-label">Last Name:</label> 
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="add-last-name" placeholder="Last Name"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="add-street-address" class="col-md-4 control-label">Street Address:</label> 
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="add-street-address" placeholder="Street Address"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="add-city" class="col-md-4 control-label">City:</label> 
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="add-city" placeholder="City"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="add-state" class="col-md-4 control-label">State:</label> 
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="add-state" placeholder="State"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="add-zip" class="col-md-4 control-label">ZIP code:</label> 
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="add-zip" placeholder="ZIP code"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <button type="submit" id="add-button" class="btn btn-default btn-primary">Create Address</button>
                            </div>
                        </div>

                    </form>

                </div>
            </div>


            <!--details modal-->
            <div class="modal fade" id="detailsModal" tabIndex="-1" role="dialog"
                 aria-labelledby="detailsModalLabel" aria-hidden="true"> 

                <div class="modal-dialog">
                    <div class="modal-content">  
                        <div class="modal-header">
                            <buttton type="button" class="close" data-dismiss="modal">
                                <span aria-hidden="true">&times;</span>
                                <span class="sr-only">Close</span>
                            </buttton>
                            <h4 class="modal-title" id="detailsModalLabel">Address Details</h4>
                        </div>

                        <div class="modal-body">
                            <h5 id="address-id"></h5>
                            <table class="table table-bordered">
                                <tr>
                                    <th>First Name:</th>
                                    <td id="address-firstName"></td>
                                </tr>
                                <tr>
                                    <th>Last Name:</th>
                                    <td id="address-lastName"></td>
                                </tr>
                                <tr>
                                    <th>Street Address</th>
                                    <td id="address-street-address"></td>
                                </tr>
                                <tr>
                                    <th>City:</th>
                                    <td id="address-city"></td>
                                </tr>
                                <tr>
                                    <th>State:</th>
                                    <td id="address-state"></td>
                                </tr>
                                <tr>
                                    <th>ZIP code:</th>
                                    <td id="address-zip"></td>
                                </tr>
                            </table>
                        </div>

                        <div class="modal-footer">
                            <buttton type="button" class="btn btn-default" data-dismiss="modal">
                                Close
                            </buttton>
                        </div>
                    </div>
                </div>

            </div>

            <!--edit modal-->
            <div class="modal fade" id="editModal" tabIndex="-1" role="dialog"
                 aria-labelledby="editModalLabel" aria-hidden="true"> 

                <div class="modal-dialog">
                    <div class="modal-content">  
                        <div class="modal-header">
                            <buttton type="button" class="close" data-dismiss="modal">
                                <span aria-hidden="true">&times;</span>
                                <span class="sr-only">Close</span>
                            </buttton>
                            <h4 class="modal-title" id="editModalLabel">Edit Address</h4>
                        </div>

                        <div class="modal-body">
                            <h5 id="address-id"></h5>

                            <form class="form-horizontal" role="form">
                                <div class="form-group">
                                    <label for="edit-firstName" class="col-md-4 control-label">First Name:</label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="edit-firstName" placeHolder="First Name" />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="edit-lastName" class="col-md-4 control-label">Last Name:</label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="edit-lastName" placeHolder="Last Name" />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="edit-street-address" class="col-md-4 control-label">Street Address:</label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="edit-company" placeHolder="Street Address" />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="edit-city" class="col-md-4 control-label">City:</label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="edit-phone" placeHolder="City" />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="edit-state" class="col-md-4 control-label">State:</label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="edit-email" placeHolder="State" />
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label for="edit-zip" class="col-md-4 control-label">ZIP code:</label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="edit-zip" placeHolder="ZIP code" />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-md-offset-4 col-md-8">
                                        <button type="submit" id="edit-button" class="btn btn-primary" data-dismiss="modal">Edit Address</button>
                                        <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
                                        <input type="hidden" id="edit-addressId"/>
                                    </div>
                                </div>
                            </form>

                        </div>

                    </div>
                </div>

            </div>

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.12.2.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/addressBook.js"></script>

    </body>
</html>

