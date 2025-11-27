package com.example.annotations.entity;

import jakarta.persistence.*;

@Entity
@Table (uniqueConstraints = @UniqueConstraint(columnNames = {"group_id", "curator_id"}))
public class GroupsCurators {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Groups group;

    @ManyToOne
    @JoinColumn(name = "curator_id", nullable = false)
    private Curators curator;
}
