package com.example.countryinfoservice_classwork.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Flags {
    private String png;
    private String svg;
}
