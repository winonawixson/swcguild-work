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
public class CurrencyConverter extends UnitConverter{

    public CurrencyConverter(double originalValue) {
        super(originalValue);
    }
    
    public void convertUsDollarToEuro(){
        convertedValue = originalValue * 0.88;
    }
    
    public void convertEuroToUsDollar(){
        convertedValue = originalValue * 1.14;
    }
    
}
