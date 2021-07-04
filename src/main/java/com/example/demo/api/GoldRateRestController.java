package com.example.demo.api;

import com.example.demo.data.source.service.GoldRateService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/gold-rate")
public class GoldRateRestController {

    private GoldRateService goldRateService;

    public GoldRateRestController(GoldRateService goldRateService) {
        this.goldRateService = goldRateService;
    }

    @ApiOperation(
            value = "Get average rate of gold prices",
            response = String.class,
            notes = "Returns average gold rate for specified date range (or for last month if no range specified)"
    )
    @GetMapping
    public ResponseEntity<String> returnAverageRate(@ApiParam(name = "sinceWhen", example = "21-07-2020", value = "Date since when you want to check average gold rate") @RequestParam Optional<String> since, @ApiParam(name = "upToWhen", example = "25-07-2020", value = "Date up to when you want to check average gold rate") @RequestParam Optional<String> upTo) {

        var goldRateString = goldRateService.getGoldRate(since, upTo);

        return ResponseEntity.ok(goldRateString);
    }

}