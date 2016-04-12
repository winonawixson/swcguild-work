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
        <!--CSS style sheet-->
        <link href="${pageContext.request.contextPath}/jsp/unitConverterStyles.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/jsp/siteStyling.css" rel="stylesheet">
        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

        <script type="text/javascript">
            function hideSourceUnits() {
                document.getElementById("tempType").style.visibility = "hidden";
            }

            function showTemp() {
                document.getElementById("tempType").style.visibility = "visible";
                document.getElementById("tempType").style.display = "block";
                document.getElementById("currencyType").style.display = "none";
                document.getElementById("volumeType").style.display = "none";
                document.getElementById("massType").style.display = "none";
            }

            function showCurrency() {
                document.getElementById("tempType").style.display = "none";
                document.getElementById("currencyType").style.display = "block";
                document.getElementById("volumeType").style.display = "none";
                document.getElementById("massType").style.display = "none";
            }

            function showVolume() {
                document.getElementById("tempType").style.display = "none";
                document.getElementById("currencyType").style.display = "none";
                document.getElementById("volumeType").style.display = "block";
                document.getElementById("massType").style.display = "none";
            }

            function showMass() {
                document.getElementById("tempType").style.display = "none";
                document.getElementById("currencyType").style.display = "none";
                document.getElementById("volumeType").style.display = "none";
                document.getElementById("massType").style.display = "block";
            }


        </script>

    </head>
    <body id="body" onload="hideSourceUnits()">
        <div class="container">
            <%@include file="header.jsp" %>

            <div class="row">
                <div class="col-md-offset-3" align="left">
                    <h2 style="color: #0f0f0f">Unit Converter</h2>
                    <form method="POST" action="unitConverter" class="form-vertical">
                        <div class="row">
                            <div class="col-md-8" id="convType">
                                <input id="temp" class=form-group" type="radio" name ="conversionType" value="temp" onclick="showTemp()" /> Temperature<br>
                                <input id="cur" class=form-group" type="radio" name ="conversionType" value="cur" onclick="showCurrency()"/> Currency<br>
                                <input id="vol" class=form-group" type="radio" name ="conversionType" value="vol" onclick="showVolume()"/> Volume<br>
                                <input id="mass" class=form-group" type="radio" name ="conversionType" value="mass" onclick="showMass()"/> Mass<br>

                            </div>
                        </div>
                        <div id="unitTypes" class="row form-group">
                            <div class="col-md-8">
                                <br>
                                <div id="tempType" style="display:block">
                                    Unit to convert from: 
                                    <select name="convertTempFromUnit">
                                        <option value="celsius">Celsius</option>
                                        <option value="fahrenheit">Fahrenheit</option>
                                        <option  value="kelvin">Kelvin</option>
                                    </select>
                                    <br><br>

                                    Unit to convert to:
                                    <select name="convertTempToUnit">
                                        <option value="celsius">Celsius</option>
                                        <option value="fahrenheit">Fahrenheit</option>
                                        <option value="kelvin">Kelvin</option>
                                    </select>
                                </div>

                                <div id="currencyType" style="display:none">
                                    Unit to convert from: 
                                    <select name="convertCurFromUnit">
                                        <option value="usDollar">US Dollar</option>
                                        <option value="euro">Euro</option>
                                    </select>
                                    <br><br>

                                    Unit to convert to:
                                    <select name="convertCurToUnit">
                                        <option value="usDollar">US Dollar</option>
                                        <option value="euro">Euro</option>
                                    </select>

                                </div>

                                <div id="volumeType" style="display:none">
                                    Unit to convert from: 
                                    <select name="convertVolFromUnit">
                                        <option value="yards">Cubic Yards</option>
                                        <option value="meters">Cubic Meters</option>
                                    </select>
                                    <br><br>

                                    Unit to convert to:
                                    <select name="convertVolToUnit">
                                        <option value="yards">Cubic Yards</option>
                                        <option value="meters">Cubic Meters</option>
                                    </select>
                                </div>

                                <div id="massType" style="display:none">
                                    Unit to convert from: 
                                    <select name="convertMassFromUnit">
                                        <option value="ounces">Ounces</option>
                                        <option value="grams">Grams</option>
                                    </select>
                                    <br><br>

                                    Unit to convert to:
                                    <select name="convertMassToUnit">
                                        <option value="ounces">Ounces</option>
                                        <option value="grams">Grams</option>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <br>
                        <div id="valueDiv" class="row form-group col-md-8">
                            Value to convert: <input type="number" name="valueToConvert" min="0"/>
                        </div>
                        <div class="row form-group col-md-8">
                            <button type="submit" value="convert" class="btn" style="color: #0f0f0f">Convert</button>
                        </div>
                </div>
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

