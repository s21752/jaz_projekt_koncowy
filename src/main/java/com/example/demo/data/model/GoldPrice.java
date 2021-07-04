package com.example.demo.data.model;

public class GoldPrice {
    private String data;
    private Double cena;

    public GoldPrice() {};

    public GoldPrice(String data, Double cena) {
        this.data = data;
        this.cena = cena;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }
}
