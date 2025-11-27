package com.example.annotations.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(
            nullable = false,
            unique = true,
            length = 10
    )
    private String name;

    @Min(1)
    @Max(5)
    @Column(nullable = false)
    private int year;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Departments department;

    @OneToMany(mappedBy = "group")
    private List<GroupsCurators> groupsCurators = new ArrayList<>();

    @OneToMany(mappedBy = "group")
    private List<GroupsLectures> groupsLectures = new ArrayList<>();
}
