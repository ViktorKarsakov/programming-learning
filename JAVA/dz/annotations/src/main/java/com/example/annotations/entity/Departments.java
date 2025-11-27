package com.example.annotations.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Departments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(
            nullable = false,
            precision = 15,
            scale = 2,
            columnDefinition = "numeric(15,2) default 0"
    )
    @DecimalMin(value = "0.00")
    private BigDecimal financing;

    @NotBlank
    @Column(
            length = 100,
            nullable = false,
            unique = true
    )
    private String name;

    @OneToMany(mappedBy = "department")
    private List<Groups> groups = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculties faculty;
}
