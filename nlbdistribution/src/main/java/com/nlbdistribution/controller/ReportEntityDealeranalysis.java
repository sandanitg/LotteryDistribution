package com.nlbdistribution.controller;

public class ReportEntityDealeranalysis {

    String lotteryn;
    Integer totquantity;

    public ReportEntityDealeranalysis(String lotteryn, Integer totquantity ){
        this.lotteryn=lotteryn; this.totquantity=totquantity;

    }

    public String getLotteryn(){
        return this.lotteryn;

    }

    public Integer getQtotuantity(){
        return this.totquantity;

    }

}
