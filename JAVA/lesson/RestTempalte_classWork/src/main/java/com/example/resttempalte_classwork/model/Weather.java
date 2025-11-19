package com.example.resttempalte_classwork.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
    public String description;
    public String icon;
}
