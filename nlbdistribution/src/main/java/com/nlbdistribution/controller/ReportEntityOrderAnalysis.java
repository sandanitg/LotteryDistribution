package com.nlbdistribution.controller;

public class ReportEntityOrderAnalysis {

    String dealer;
    Integer quantity;

    public ReportEntityOrderAnalysis(String dealer, Integer quantity ){
        this.dealer=dealer; this.quantity=quantity;

    }

    public String getDealer(){
        return this.dealer;

    }

    public Integer getQuantity(){
        return this.quantity;

    }

}
