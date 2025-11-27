package com.example.annotations.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank
    @Column(
            nullable = false,
            unique = true
    )
    private String name;

    @OneToMany(mappedBy = "subject")
    private List<Lectures> lectures = new ArrayList<>();

}
