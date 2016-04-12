/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.vendingmachine.model;

/**
 *
 * @author apprentice
 */
public class MakeChange {

    private int quarters, dimes, nickles, pennies;
    private double startingAmount; //in pennies

    public int getQuarters() {
        return quarters;
    }

    public int getDimes() {
        return dimes;
    }

    public int getNickels() {
        return nickles;
    }

    public int getPennies() {
        return pennies;
    }

    public double getStartingAmount() {
        return startingAmount;
    }

    public void makeChange(double startingAmount) {
        this.startingAmount = 100 * startingAmount;
        double rollingBalance = this.startingAmount;

        this.quarters = (int) (this.startingAmount / 25);
        rollingBalance = rollingBalance - this.quarters * 25;

        this.dimes = (int) rollingBalance / 10;
        rollingBalance = rollingBalance - this.dimes * 10;

        this.nickles = (int) rollingBalance / 5;
        rollingBalance = rollingBalance - this.nickles * 5;

        this.pennies = (int) rollingBalance;

    }
}
