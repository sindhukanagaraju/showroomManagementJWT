package com.example.showroommanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "Sales")
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="transaction")
    private Date transaction;
    @ManyToOne()
    @JoinColumn(name ="product_id")
    private Product product;
    @ManyToOne()
    @JoinColumn(name ="customer_id")
    private Customer customer;

}
