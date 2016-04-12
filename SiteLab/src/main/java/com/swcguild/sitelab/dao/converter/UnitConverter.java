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
public class UnitConverter {
    
    protected double originalValue;
    protected double convertedValue;

    public UnitConverter(double originalValue) {
        this.originalValue = originalValue;
    }

    public double getOriginalValue() {
        return originalValue;
    }

    public double getConvertedValue() {
        return convertedValue;
    }

    public void setOriginalValue(double originalValue) {
        this.originalValue = originalValue;
    }

    public void setConvertedValue(double convertedValue) {
        this.convertedValue = convertedValue;
    }

}
