/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.sitelab.dao;

/**
 *
 * @author Winona Wixson
 */
public class FlooringCalculator {

    private double width;
    private double length;
    private double costPerSqFt;
    private double flooringCost;
    private double laborCost;
    private double laborTime; // number of quarter of hours
    private double area;

    private final double LABORCOST_PER_QUARTER_HOUR = 86 / 4;
    
    public FlooringCalculator(double width, double length, double costPerSqFt) {
        this.width = width;
        this.length = length;
        this.costPerSqFt = costPerSqFt;

        area = width * length;
        flooringCost = area * costPerSqFt;
        
        // labor cost....5sqft/15 min = $21.50
        
        double leftOverFt = area % 5;
        laborTime = (area / 5) - leftOverFt;
        if(leftOverFt > 0){
            laborTime = laborTime + 1;
        }
        
        laborCost = laborTime * LABORCOST_PER_QUARTER_HOUR;             
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }

    public double getCostPerSqFt() {
        return costPerSqFt;
    }

    public double getFlooringCost() {
        return flooringCost;
    }

    public double getLaborCost() {
        return laborCost;
    }

    public double getLaborTime() {
        return laborTime;
    }

    public double getArea() {
        return area;
    }

    public double getLABORCOST_PER_QUARTER_HOUR() {
        return LABORCOST_PER_QUARTER_HOUR;
    }


}
