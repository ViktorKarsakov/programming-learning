package com.example.countryinfoservice_classwork.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Name {
    private String common;
    private String official;
}
