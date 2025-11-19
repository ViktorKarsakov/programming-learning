package com.example.dz_svyazi_v_bd.entity.ManyToMany;

import jakarta.persistence.*;

@Entity
public class MovieActor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "actor_id")
    private Movie movie;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Actor actor;
}
