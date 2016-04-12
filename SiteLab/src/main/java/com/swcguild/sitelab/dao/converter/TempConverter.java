/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.sitelab.dao.converter;

/**
 *
 * @author Winona Wixson
 */
public class TempConverter extends UnitConverter {

    public TempConverter(double originalValue) {
        super(originalValue);
    }
    
    public void convertFahrenheitToCelsius(){
        convertedValue = (originalValue - 32) * (5.0 / 9.0);
    }
    
    public void convertFahrenheitToKelvin(){
        convertedValue = ((originalValue - 32) * 5.0 / 9.0) + 273.15;
    }
    
    public void convertCelsiusToFahrenheit(){
         convertedValue = (originalValue * 9.0 / 5.0) + 32;
    }
    
    public void convertCelsiusToKelvin(){
        convertedValue = originalValue + 273.15;
    }
    
    public void convertKelvinToFahrenheit(){
        convertedValue = (originalValue - 273.15) * (9.0 / 5.0) + 32;
    }
    
    public void convertKelvinToCelsius(){
        convertedValue = originalValue - 273.15;
    }
    
}
