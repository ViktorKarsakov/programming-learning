package com.example.resttempalte_classwork.service;

import com.example.resttempalte_classwork.model.SimpleCache;
import com.example.resttempalte_classwork.model.WeatherApiResponse;
import com.example.resttempalte_classwork.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    private SimpleCache cache;
    private RestTemplate restTemplate;

    @Value("${weather.api-key}")
    private String apiKey;

    @Value("${weather.url}")
    private String url;

    public WeatherService(SimpleCache cache, RestTemplate restTemplate) {
        this.cache = cache;
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<?> getWeatherByCityName(String city) {
        WeatherResponse cached = (WeatherResponse) cache.get(city);
        if (cached != null) {
            System.out.println("Request from CACHE: " + city);
            return ResponseEntity.ok(cached);
        }
        System.out.println("Request from API: " + city);

        String fullUrl = url + "?q=" + city + "&appid=" + apiKey + "&units=metric";
        ResponseEntity<WeatherApiResponse> response = restTemplate.getForEntity(fullUrl, WeatherApiResponse.class);
        WeatherApiResponse api = response.getBody();
        WeatherResponse dto = new WeatherResponse();
        dto.city = api.name != null ? api.name : "";
        dto.temperature = api.main != null ? api.main.temp : 0.0;
        dto.description = (api.weather != null && !api.weather.isEmpty()) ? api.weather.get(0).description : "";
        dto.humidity = api.main != null ? api.main.humidity : 0;
        dto.windSpeed = api.wind != null ? api.wind.speed : 0.0;
        dto.icon = (api.weather != null && !api.weather.isEmpty()) ? api.weather.getFirst().icon : "";

        cache.put(city, dto);
        return ResponseEntity.ok(dto);
        }

}
