package com.example.demo.controller;

import com.example.demo.model.MovieApiResponse;
import com.example.demo.model.Person;
import com.example.demo.model.SimpleCache;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/home")
public class HomeController {

    Map<String, MovieApiResponse> cacheData = new HashMap<>();
    SimpleCache cache = new SimpleCache(60 * 1000);


    @GetMapping("/search")
    public ResponseEntity<MovieApiResponse> search(
            @RequestParam String title,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size // Dz
            ) {

        //V kache ucitivat takje i page i size


        MovieApiResponse cached = cache.get(title);
        if (cached != null) {
            System.out.println("Zapros iz KESHA : " + title);
            return ResponseEntity.ok(cached);
        }
        System.out.println("Zapros iz API : " + title);
        RestTemplate restTemplate = new RestTemplate();
        MovieApiResponse result = new MovieApiResponse();
        ResponseEntity<MovieApiResponse> response;


        title = title.toUpperCase();
        String url = "https://www.omdbapi.com/?s=" + title + "&apiKey=266b43cd&page=" + page;
        System.out.println("Send request # 1");
        response = restTemplate.getForEntity(url, MovieApiResponse.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            result = response.getBody();

            int totalResult = Integer.parseInt(result.totalResults);
            int pages = (int) Math.ceil(totalResult / 10.0);

            System.out.println("pages: " + pages);
            System.out.println("totalResult: " + totalResult);

            for (int i = 2; i <= pages; i++) {
                url = "https://www.omdbapi.com/?s=" + title + "&apiKey=266b43cd&page=" + i;
                System.out.println("Send request # " + i);
                response = restTemplate.getForEntity(url, MovieApiResponse.class);
                if (response.getStatusCode().is2xxSuccessful()) {
                    MovieApiResponse body = response.getBody();
                    result.search.addAll(body.search);

                }
            }
        }

        cache.put(title,result);
        System.out.println("Save in cached!!!");

        return ResponseEntity.ok(result);
}

//    @GetMapping("/search")
//    public ResponseEntity<MovieApiResponse> search(
//            @RequestParam String title,
//            @RequestParam(required = false,defaultValue = "1") Integer page) {
//        RestTemplate restTemplate = new RestTemplate();
//        MovieApiResponse result = new MovieApiResponse();
//        ResponseEntity<MovieApiResponse> response;
//
//        title = title.toUpperCase();
//
//        if (cacheData.containsKey(title)) {
//            result = cacheData.get(title);
//        }else{
//            String url = "https://www.omdbapi.com/?s=" + title + "&apiKey=266b43cd&page=" + page;
//            System.out.println("Send request # 1");
//            response =  restTemplate.getForEntity(url, MovieApiResponse.class);
//
//            if (response.getStatusCode().is2xxSuccessful()) {
//                result = response.getBody();
//
//                int totalResult = Integer.parseInt(result.totalResults);
//                int pages = (int) Math.ceil(totalResult / 10.0);
//
//                System.out.println("pages: " + pages);
//                System.out.println("totalResult: " + totalResult);
//
//                for (int i = 2; i <= pages; i++) {
//                    url = "https://www.omdbapi.com/?s=" + title + "&apiKey=266b43cd&page=" + i;
//                    System.out.println("Send request # "+ i);
//                    response =  restTemplate.getForEntity(url, MovieApiResponse.class);
//                    if (response.getStatusCode().is2xxSuccessful()) {
//                        MovieApiResponse body = response.getBody();
//                        result.search.addAll(body.search);
//
//                    }
//                }
//            }
//            cacheData.put(title,result);
//        }
//        return ResponseEntity.ok(result);
//    }

//    @GetMapping("/search")
//    public ResponseEntity<String> search(
//            @RequestParam String title,
//            @RequestParam(required = false,defaultValue = "1") Integer page) {
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "https://www.omdbapi.com/?s=" + title + "&apiKey=266b43cd&page=" + page;
//        ResponseEntity<String> result =  restTemplate.getForEntity(url, String.class);
//        return result;
//    }


//    @GetMapping("/search")
//    public ResponseEntity<String> getAll() {
//        RestTemplate restTemplate = new RestTemplate();
//
//        String url = "https://www.omdbapi.com/?s=Hulk&apiKey=266b43cd";
/// /        String result =  restTemplate.getForObject(url, String.class);
//        ResponseEntity<String> result =  restTemplate.getForEntity(url, String.class);
//
//        if (result.getStatusCode().is2xxSuccessful()) {
//            return ResponseEntity.ok(result.getBody());
//        }
//        HttpStatusCode statusCode = result.getStatusCode();
//        if (statusCode == HttpStatus.OK) {
//            return ResponseEntity.ok(result.getBody());
//        }else {
//            return ResponseEntity.status(statusCode).body(result.getBody());
//        }
//
//        return result;
//    }
}
