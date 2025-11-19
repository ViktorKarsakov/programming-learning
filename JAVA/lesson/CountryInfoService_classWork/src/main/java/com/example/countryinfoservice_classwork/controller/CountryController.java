package com.example.countryinfoservice_classwork.controller;

import com.example.countryinfoservice_classwork.model.CountryApiResponse;
import com.example.countryinfoservice_classwork.model.CountryResponse;
import com.example.countryinfoservice_classwork.model.SimpleCache;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/countries")
public class CountryController {
    private SimpleCache cache = new SimpleCache(30 * 60 * 1000);
    private static final String BASE_URL = "https://restcountries.com/v3.1";
    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/search")
    public ResponseEntity<?> searchCountry(@RequestParam("name") String name) {
        CountryResponse cached = (CountryResponse) cache.get(name);
        if (cached != null) {
            System.out.println("Request from CACHE: " + name);
            return ResponseEntity.ok(cached);
        }
        System.out.println("Request from API: " + name);


        String url = "https://restcountries.com/v3.1/name/" + name;
        ResponseEntity<CountryApiResponse> response = restTemplate.getForEntity(url, CountryApiResponse.class);
        CountryApiResponse api = response.getBody();
        CountryResponse dto = new CountryResponse();

    }
}
