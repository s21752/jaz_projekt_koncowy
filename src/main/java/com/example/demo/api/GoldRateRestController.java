package com.example.demo.api;

import com.example.demo.data.source.service.GoldRateService;
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

    @GetMapping
    public ResponseEntity<String> returnAverageRate(@RequestParam Optional<String> since, @RequestParam Optional<String> upTo) {

        var goldRateString = goldRateService.getGoldRate(since, upTo);

        return ResponseEntity.ok(goldRateString);
    }

}