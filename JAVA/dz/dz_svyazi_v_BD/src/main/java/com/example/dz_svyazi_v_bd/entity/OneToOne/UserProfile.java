package com.example.dz_svyazi_v_bd.entity.OneToOne;

import jakarta.persistence.*;

@Entity
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private int age;
    private String email;
    private String phone;
    private String address;
    private String photo;

    @OneToOne(mappedBy = "userProfile")
    private User user;
}
