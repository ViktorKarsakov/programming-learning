package com.example.dz_svyazi_v_bd.entity.ManyToMany;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private int age;

    @OneToMany(mappedBy = "actor")
    private Set<MovieActor> movieActors = new HashSet<MovieActor>();
}
