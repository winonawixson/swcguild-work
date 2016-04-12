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
public class PurchaseResult {

    private double inputMoney;
    private Item item;
    private double change;
    private boolean sufficientFunds;
    private boolean sufficientQuant;

    public PurchaseResult(double inputMoney, Item item) {
        this.inputMoney = inputMoney;
        this.item = item;
       
        if (inputMoney >= item.getItemPrice()) {
            sufficientFunds = true;
        }
        if(item.getItemQuantity() > 0){
            sufficientQuant = true;
        }
        
        if(sufficientFunds && sufficientQuant){
            change = inputMoney - item.getItemPrice();
        }
    }

    public double getInputMoney() {
        return inputMoney;
    }

    public void setInputMoney(double inputMoney) {
        this.inputMoney = inputMoney;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public boolean isSufficientFunds() {
        return sufficientFunds;
    }

    public void setSufficientFunds(boolean sufficientFunds) {
        this.sufficientFunds = sufficientFunds;
    }

    public boolean isSufficientQuant() {
        return sufficientQuant;
    }

    public void setSufficientQuant(boolean sufficientQuant) {
        this.sufficientQuant = sufficientQuant;
    }

}
