package com.example.demo.data.model;

import java.util.ArrayList;
import java.util.List;

public class GoldPricesWrapper {

    public List<GoldPrice> getGoldPriceList() {
        return goldPriceList;
    }

    public void setGoldPriceList(List<GoldPrice> goldPriceList) {
        this.goldPriceList = goldPriceList;
    }

    private List<GoldPrice> goldPriceList;

    public GoldPricesWrapper() {
        goldPriceList = new ArrayList<>();
    }
}
