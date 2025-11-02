package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

//public class Movie {
//    @JsonProperty("Title")
//    public String title;
//    @JsonProperty("Year")
//    public String year;
//    public String imdbID;
//    @JsonProperty("Type")
//    public String type;
//    @JsonProperty("Poster")
//    public String poster;
//}




public class Movie{
    @JsonProperty("Title")
    public String title;
    @JsonProperty("Year")
    public String year;
    public String imdbID;
    @JsonProperty("Type")
    public String type;
    @JsonProperty("Poster")
    public String poster;
}
