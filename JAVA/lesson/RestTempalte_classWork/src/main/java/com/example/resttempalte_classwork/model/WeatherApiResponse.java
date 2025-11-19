package com.example.resttempalte_classwork.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherApiResponse {
    public String name;
    public Main main;
    public List<Weather> weather;
    public Wind wind;
}
