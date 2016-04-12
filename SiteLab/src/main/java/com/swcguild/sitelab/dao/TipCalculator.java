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
public class TipCalculator {
    
    double subtotal;
    double tipPercent;
    double tip;
    double total;

    public TipCalculator(double subtotal, double tipPercent) {
        this.subtotal = subtotal;
        this.tipPercent = tipPercent;
        
        this.tip = subtotal * tipPercent / 100;
        this.total = subtotal + tip;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getTipPercent() {
        return tipPercent;
    }

    public double getTip() {
        return tip;
    }

    public double getTotal() {
        return total;
    }
}
