package com.example.dz_svyazi_v_bd.entity.ManyToMany;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String movieName;
    private String description;
    private int year;

    @OneToMany(mappedBy = "movie")
    private Set<MovieActor> movieActors = new HashSet<MovieActor>();
}
