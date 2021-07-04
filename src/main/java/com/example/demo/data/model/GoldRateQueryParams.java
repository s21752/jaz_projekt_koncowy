package com.example.demo.data.model;

import com.example.demo.utils.InstantFormatter;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class GoldRateQueryParams {

    private String sinceDate;
    private String upToDate;

    public static GoldRateQueryParams getDefault() {
        return new GoldRateQueryParams(
                InstantFormatter.getDataString(Instant.now().minus(1, ChronoUnit.MONTHS)),
                InstantFormatter.getDataString(Instant.now()));
    }

    public GoldRateQueryParams(String sinceDate, String upToDate) {
        this.sinceDate = sinceDate;
        this.upToDate = upToDate;
    }

    public String getSinceDate() {
        return sinceDate;
    }

    public void setSinceDate(String sinceDate) {
        this.sinceDate = sinceDate;
    }

    public String getUpToDate() {
        return upToDate;
    }

    public void setUpToDate(String upToDate) {
        this.upToDate = upToDate;
    }
}
