package com.example.resttemplate.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/home")
public class HomeController {

    @GetMapping("/search")
    public ResponseEntity<String> search(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://www.omdbapi.com/?s=Hulk&apikey=ea5c4c75";
        ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);
        HttpStatusCode statusCode = result.getStatusCode();

        if (result.getStatusCode().is2xxSuccessful()){
            return ResponseEntity.ok().body(result.getBody());
        } else {
            return ResponseEntity.status(statusCode).body(result.getBody());
        }
        return result;
    }

}
