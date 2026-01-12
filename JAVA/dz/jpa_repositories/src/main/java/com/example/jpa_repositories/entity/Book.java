package com.example.jpa_repositories.entity;

import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String isbn;
    private Integer publishedYear;
    private double price;
    private Integer pages;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}
