package com.example.demo.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateModule {

    @Bean
    public RestTemplate getRestTemplateBean() { return new RestTemplate(); }
}
