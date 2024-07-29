package com.example.toolsproject.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "meal")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mealId;

    private String name;

    private float price;

    private Long restaurantId;

}
