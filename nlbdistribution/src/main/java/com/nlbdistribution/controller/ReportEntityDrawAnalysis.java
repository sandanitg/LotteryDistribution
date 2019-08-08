package com.nlbdistribution.controller;

public class ReportEntityDrawAnalysis {

    String dealer;
    Integer quantity;

    public ReportEntityDrawAnalysis(String dealer, Integer quantity ){
        this.dealer=dealer; this.quantity=quantity;

    }

    public String getDealer(){
        return this.dealer;

    }

    public Integer getQuantity(){
        return this.quantity;

    }
}
