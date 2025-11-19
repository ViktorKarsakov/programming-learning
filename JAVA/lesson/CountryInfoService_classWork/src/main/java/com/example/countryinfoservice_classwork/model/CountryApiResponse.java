package com.example.countryinfoservice_classwork.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Currency;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryApiResponse {
    private Name name;
    private List<String> capital;
    private long population;
    private double area;
    private String region;
    private String subregion;
    private Map<String, String> languages;
    private Flags flags;
    private  Map<String, Currency> currencies;


}
