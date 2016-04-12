/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.sitelab.controller;

import com.swcguild.sitelab.dao.converter.CurrencyConverter;
import com.swcguild.sitelab.dao.converter.MassConverter;
import com.swcguild.sitelab.dao.converter.TempConverter;
import com.swcguild.sitelab.dao.converter.UnitConverter;
import com.swcguild.sitelab.dao.converter.VolumeConverter;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Winona Wixson
 */
@Controller
public class UnitConverterController {

    @RequestMapping(value = "/unitConverter", method = RequestMethod.POST)
    public String playUnitConverter(HttpServletRequest request) {

        String convType = request.getParameter("conversionType");
        String valueString = request.getParameter("valueToConvert");

        String convertFromUnit;// = request.getParameter("convertFromUnit");
        String convertToUnit;// = request.getParameter("convertToUnit");

        try {
            double value = Double.parseDouble(valueString);

            switch (convType) {
                case "temp":
                    convertFromUnit = request.getParameter("convertTempFromUnit");
                    convertToUnit = request.getParameter("convertTempToUnit");
                    if (convertFromUnit.equals(convertToUnit)) {
                        UnitConverter converter = new UnitConverter(value);
                        converter.setConvertedValue(value);
                        request.setAttribute("converter", converter);
                    } else {
                        //request = 
                        convertTemperature(request, value, convertFromUnit, convertToUnit);
                    }
                    break;
                case "cur":
                    convertFromUnit = request.getParameter("convertCurFromUnit");
                    convertToUnit = request.getParameter("convertCurToUnit");
                    if (convertFromUnit.equals(convertToUnit)) {
                        UnitConverter converter = new UnitConverter(value);
                        converter.setConvertedValue(value);
                        request.setAttribute("converter", converter);
                    } else {       //request = 
                        convertCurrency(request, value, convertFromUnit, convertToUnit);
                    }
                    break;
                case "vol":
                    convertFromUnit = request.getParameter("convertVolFromUnit");
                    convertToUnit = request.getParameter("convertVolToUnit");
                    if (convertFromUnit.equals(convertToUnit)) {
                        UnitConverter converter = new UnitConverter(value);
                        converter.setConvertedValue(value);
                        request.setAttribute("converter", converter);
                    } else {
                        //request = 
                        convertVolume(request, value, convertFromUnit, convertToUnit);
                    }
                    break;
                case "mass":
                    convertFromUnit = request.getParameter("convertMassFromUnit");
                    convertToUnit = request.getParameter("convertMassToUnit");
                    if (convertFromUnit.equals(convertToUnit)) {
                        UnitConverter converter = new UnitConverter(value);
                        converter.setConvertedValue(value);
                        request.setAttribute("converter", converter);
                    } else {
                        //request = 
                        convertMass(request, value, convertFromUnit, convertToUnit);
                    }
                    break;
                default:
                    break;
            }

        } catch (NumberFormatException ex) {
            request.setAttribute("badInput", true);
        }

        return "ucresponse";
    }

    public void convertTemperature(HttpServletRequest request, double value,
            String convertFromUnit, String convertToUnit) {

        TempConverter converter = new TempConverter(value);

        if (convertFromUnit.equals("fahrenheit")) {
            if (convertToUnit.equals("celsius")) {
                converter.convertFahrenheitToCelsius();
            } else {
                converter.convertFahrenheitToKelvin();
            }
        } else if (convertFromUnit.equals("celsius")) {
            if (convertToUnit.equals("fahrenheit")) {
                converter.convertCelsiusToFahrenheit();
            } else {
                converter.convertCelsiusToKelvin();
            }
        } else if (convertToUnit.equals("fahrenheit")) {
            converter.convertKelvinToFahrenheit();
        } else if (convertToUnit.equals("celsius")) {
            converter.convertKelvinToCelsius();
        }
        request.setAttribute("converter", converter);
        // return request;
    }

    public void convertCurrency(HttpServletRequest request, double value,
            String convertFromUnit, String convertToUnit) {

        CurrencyConverter converter = new CurrencyConverter(value);

        if (convertFromUnit.equals("usDollar")) {
            converter.convertUsDollarToEuro();
        } else if (convertFromUnit.equals("euro")) {
            converter.convertEuroToUsDollar();
        }

        request.setAttribute("converter", converter);

        //return request;
    }

    public void convertVolume(HttpServletRequest request, double value,
            String convertFromUnit, String convertToUnit) {

        VolumeConverter converter = new VolumeConverter(value);

        if (convertFromUnit.equals("yards")) {
            converter.convertCubicYardsToCubicMeters();
        } else if (convertFromUnit.equals("meters")) {
            converter.convertCubicMetersToCubicYards();
        }

        request.setAttribute("converter", converter);

        //return request;
    }

    public void convertMass(HttpServletRequest request, double value,
            String convertFromUnit, String convertToUnit) {

        MassConverter converter = new MassConverter(value);

        if (convertFromUnit.equals("ounces")) {
            converter.convertOuncesToGrams();
        } else if (convertFromUnit.equals("grams")) {
            converter.convertGramsToOunces();
        }

        request.setAttribute("converter", converter);

        //return request;
    }

}
