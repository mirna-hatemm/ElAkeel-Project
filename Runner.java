package com.example.toolsproject.Entities;

import com.example.toolsproject.Status.RunnerStatus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "runner")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Runner {

    @Id
    private String username;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long runnerId;

    private String name;

    private String password;

    @Enumerated(EnumType.STRING)
    private RunnerStatus status;

    private float deliveryFee;

}
