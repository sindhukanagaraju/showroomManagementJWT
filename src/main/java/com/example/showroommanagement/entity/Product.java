package com.example.showroommanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "model")
    private String model;
    @Column(name = "price")
    private Double price;
    @Column(name = "colour")
    private String colour;

    @ManyToOne()
    @JoinColumn(name = "salesman_id")
    private SalesMan salesMan;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
