package com.example.jpa_repositories.controller;

import com.example.jpa_repositories.entity.Author;
import com.example.jpa_repositories.repository.AuthorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

    private AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping("/find-email")
    public ResponseEntity<?> findByEmail(String email){
        Author author = (Author) authorRepository.findByEmail(email);
        if (author == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(author);
    }

    @GetMapping("/find-age")
    public ResponseEntity<?> findByAge(int age){
        Author author = (Author) authorRepository.findByAgeGreaterThan(age);
        return ResponseEntity.ok(author);
    }

    @GetMapping("/find-age-postseason")
    public ResponseEntity<?> findByAgePostseason(int minAge, int maxAge){
        Author author = (Author) authorRepository.findByAgeBetween(minAge, maxAge);
        return ResponseEntity.ok(author);
    }

    @GetMapping("/find-by-country")
    public ResponseEntity<?> findByCountry(String country){
        Author author = (Author) authorRepository.findByCountry(country);
        return ResponseEntity.ok(author);
    }

    @GetMapping("/find-by-name")
    public ResponseEntity<?> findByName(String firstName, String lastName){
        Author author = (Author) authorRepository.findByFirstNameOrLastName(firstName, lastName);
        return ResponseEntity.ok(author);
    }

    @GetMapping("/find-by-country-age")
    public ResponseEntity<?> findByCountryAge(String country){
        Author author = (Author) authorRepository.findByCountryOrderByAgeDesc(country);
        return ResponseEntity.ok(author);
    }

    @GetMapping("/find-top-3-country")
    public ResponseEntity<?> findByTop3Country(){
        Author author = (Author) authorRepository.findTop3ByCountryOrderByAgeDesc();
        return ResponseEntity.ok(author);
    }


}
