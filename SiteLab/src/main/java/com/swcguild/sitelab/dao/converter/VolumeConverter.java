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
public class VolumeConverter extends UnitConverter {

    public VolumeConverter(double originalValue) {
        super(originalValue);
    }
    
    public void convertCubicYardsToCubicMeters(){
        convertedValue = originalValue * 1.30795;
    }
    
    public void convertCubicMetersToCubicYards(){
        convertedValue = originalValue * 0.764555;
    }
}
