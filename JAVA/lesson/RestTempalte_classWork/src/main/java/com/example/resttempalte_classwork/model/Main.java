package com.example.resttempalte_classwork.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Main {
    public double temp;
    public int humidity;
    public int pressure;
}
