package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

//public class MovieApiResponse {
//    @JsonProperty("Search")
//    public ArrayList<Movie> search;
//    public String totalResults;
//    @JsonProperty("Response")
//    public String response;
//
//}


public class MovieApiResponse{
    @JsonProperty("Search")
    public ArrayList<Movie> search;
    public String totalResults;
    @JsonProperty("Response")
    public String response;
}