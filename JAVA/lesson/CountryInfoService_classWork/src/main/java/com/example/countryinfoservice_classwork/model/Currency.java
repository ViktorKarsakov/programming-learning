package com.example.countryinfoservice_classwork.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Currency {
    private String name;
    private String symbol;
}
