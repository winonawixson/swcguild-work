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
public class AnnualInterest {

    double principalAtBeg = 0;
    double earnedYearlyInterest; //do this for each year
    double principalAtEnd = 0; //grand total

    public AnnualInterest(double annualRate, double initialPrincipal) {

        principalAtBeg = initialPrincipal;
        double currentBalance = initialPrincipal;

        //for loop, models earning interest for a year 
        for (int j = 0; j < 4; j++) {

            //calculate principalAtEnd currentBalance
            currentBalance = currentBalance * (1 + annualRate / 400);

        }

        principalAtEnd = currentBalance;
        earnedYearlyInterest = principalAtEnd - principalAtBeg;

    }

    public double getPrincipalAtBeg() {
        return principalAtBeg;
    }

    public double getEarnedYearlyInterest() {
        return earnedYearlyInterest;
    }

    public double getPrincipalAtEnd() {
        return principalAtEnd;
    }

    
    
}
