package com.example.annotations.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Teachers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String surname;

    @DecimalMin(value = "0.00", inclusive = false)
    @Column(
            nullable = false,
            precision = 15,
            scale = 2
    )
    private BigDecimal salary;

    @OneToMany(mappedBy = "teachers")
    private List<Lectures> lectures = new ArrayList<>();
}
