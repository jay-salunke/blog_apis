package com.example.blogapis.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "users")
@NoArgsConstructor
@Getter
@Setter
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "email_id", nullable = false,length = 150)
    private String email;

    @Column(name = "user_name" ,nullable = false, length = 150)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(length = 255)
    private String about;


}
