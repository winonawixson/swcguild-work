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
public class MassConverter extends UnitConverter{

    public MassConverter(double originalValue) {
        super(originalValue);
    }
    
    public void convertOuncesToGrams(){
        convertedValue = originalValue * 28.3495;
    }
    
    public void convertGramsToOunces(){
        convertedValue = originalValue * 0.035274;
    }
    
}
