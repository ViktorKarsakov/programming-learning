package com.example.resttempalte_classwork.controller;

import com.example.resttempalte_classwork.model.SimpleCache;
import com.example.resttempalte_classwork.model.WeatherApiResponse;
import com.example.resttempalte_classwork.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class WeatherController {
    private SimpleCache cache;

    @Autowired
    public WeatherController(SimpleCache cache) {
        this.cache = cache;
    }

    @GetMapping("/weather")

    }
}
