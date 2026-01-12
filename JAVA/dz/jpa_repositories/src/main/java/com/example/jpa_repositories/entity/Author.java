package com.example.jpa_repositories.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
    private String country;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "author")
    List<Book> books = new ArrayList<>();
}
