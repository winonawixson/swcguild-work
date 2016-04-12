/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.sitelab.dao;

import java.util.HashMap;

/**
 *
 * @author Rene Gomez and Winona Wixson
 */
public class InterestCalculator {

    double annualRate; //get user
    double initialPrincipal; //get user
    int years; //get user
    int currentYear = 2016; //year interest was earned 
    double principalAtBeg = 0;
    double earnedYearlyInterest; //do this for each year
    double principalAtEnd = 0; //grand total
    
    HashMap<Integer, AnnualInterest> annualInterestData = new HashMap<>();

    public InterestCalculator(double annualRate, double initialPrincipal, int years) {
        this.annualRate = annualRate;
        this.initialPrincipal = initialPrincipal;
        this.years = years;
        
        calculateInterest();

    }

    public void calculateInterest() {

        principalAtBeg = initialPrincipal;
        double currentBalance = initialPrincipal;

        for (int i = 0; i < years; i++) {
        
            //calculating the interest for each year
            AnnualInterest annualInterest = new AnnualInterest(annualRate, principalAtBeg);
            
            //putting the data in the hashmap
            annualInterestData.put(currentYear, annualInterest);
            
            //updating values for new year
            principalAtBeg = annualInterest.getPrincipalAtEnd();
            currentYear++;

        }
    }

    public HashMap<Integer, AnnualInterest> getAnnualInterestData() {
        return annualInterestData;
    }
}
