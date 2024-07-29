package com.example.toolsproject.Entities;

import com.example.toolsproject.Status.OrderStatus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private float totalPrice;

    private Long runnerId;

    private Long restaurantId;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany
    private List<Meal> meals;

}
