package com.example.toolsproject.Entities;

import com.example.toolsproject.Roles.Roles;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String username;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;


    private String password;

    @Enumerated(EnumType.STRING)
    Roles role;

}
