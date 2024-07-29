package com.example.toolsproject.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "restaurant")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantId;

    private String name;

    private Long ownerId;

    private double earns;

    @OneToMany(mappedBy = "restaurantId", fetch = FetchType.EAGER)
    private List<Meal> meals;

    @OneToMany(mappedBy = "restaurantId", fetch = FetchType.EAGER)
    private List<Order> orders;

}
