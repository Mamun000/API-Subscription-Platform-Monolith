package com.mamun.apiplatform.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExternalApiController {

    @GetMapping("/api/v1/external/weather")
    public String weatherApi() {
        return "Weather data from external API!";
    }
}