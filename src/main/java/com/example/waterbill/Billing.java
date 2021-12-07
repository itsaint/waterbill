package com.example.waterbill;

/**
 * Billing module at waterbill
 * 
 */
public class Billing {

    protected BillCalculator calc ;

    public Billing(String config, BillCalculator calc){
        this.calc = calc;
        //setup db etc 
    }

    //formats the bill 
    public String getBill(Consumer consumer){
        return String.format("%d %d",consumer.getProduct(), this.calc.getBillAmount(consumer));
    }

}
