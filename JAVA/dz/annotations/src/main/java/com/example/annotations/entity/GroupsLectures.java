package com.example.annotations.entity;

import jakarta.persistence.*;

@Entity
public class GroupsLectures {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Groups group;

    @ManyToOne
    @JoinColumn(name = "lecture_id", nullable = false)
    private Lectures lecture;
}
