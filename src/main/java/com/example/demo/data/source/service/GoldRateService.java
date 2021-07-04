package com.example.demo.data.source.service;

import com.example.demo.data.model.GoldPrice;
import com.example.demo.data.model.GoldRateQueryParams;
import com.example.demo.utils.InstantFormatter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.time.Instant;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GoldRateService {

    private RestTemplate goldRateRestTemplate;

    private String nbpApiBaseUrl = "http://api.nbp.pl/api/";

    public GoldRateService(RestTemplate restTemplate) {
        this.goldRateRestTemplate = restTemplate;
    }

    public String getGoldRate(Optional<String> since, Optional<String> upTo) {
        GoldRateQueryParams queryParams;
        if (since.isPresent() && upTo.isPresent()) {
            // get for since and up to
            queryParams = new GoldRateQueryParams(since.get(), upTo.get());
        } else
            queryParams = since.map(s -> new GoldRateQueryParams(s, InstantFormatter.getDataString(Instant.now()))).orElseGet(GoldRateQueryParams::getDefault);

        return getAndCacheGoldRate(queryParams);
    }

    private String getAndCacheGoldRate(GoldRateQueryParams queryParams) {
        Instant from = InstantFormatter.getInstant(queryParams.getSinceDate());
        Instant upTo = InstantFormatter.getInstant(queryParams.getUpToDate());

        var queryUrl = nbpApiBaseUrl + "cenyzlota/" + InstantFormatter.getNbpApiDateQueryStringFormat(from) +
                "/" + InstantFormatter.getNbpApiDateQueryStringFormat(upTo) + "/";

        System.out.println(queryUrl);

        GoldPrice[] goldPrices = goldRateRestTemplate.getForObject(queryUrl, GoldPrice[].class);

        StringBuffer averageRateResponse = new StringBuffer();

        averageRateResponse.append("Average gold rate since: ");
        averageRateResponse.append(queryParams.getSinceDate());
        averageRateResponse.append(" up to: ");
        averageRateResponse.append(queryParams.getUpToDate());
        averageRateResponse.append(" is: ");
        if (goldPrices == null || goldPrices.length <= 0)
            averageRateResponse.append(0.0);
        else {
            Double sum = 0.0;
            var pricesList = Arrays.stream(goldPrices).map((GoldPrice::getCena)).collect(Collectors.toList());
            for (Double price : pricesList) {
                sum += price;
            }

            var averageFormat = new DecimalFormat("#.##");
            averageRateResponse.append(averageFormat.format(sum / pricesList.size()));
        }

        return averageRateResponse.toString();
    }
}
