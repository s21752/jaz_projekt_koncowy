package com.example.demo.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gold-rate")
public class GoldRateRestController {

    @GetMapping
    public ResponseEntity<String> returnAverageRate(@RequestParam String since, @RequestParam String upTo) {

        return ResponseEntity.ok("You requested gold rate value from " + since + " up to " + upTo);
    }

}
