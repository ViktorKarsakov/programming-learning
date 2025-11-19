package com.example.dz_svyazi_v_bd.entity.OneToOne;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String lastname;
    private int age;

    @OneToOne
    @JoinColumn(name = "userProfile_id")
    private UserProfile userProfile;
}
