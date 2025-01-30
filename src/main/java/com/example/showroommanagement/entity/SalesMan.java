package com.example.showroommanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "salesman")
public class SalesMan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "salary")
    private Double salary;

    @Column(name = "Address")
    private String address;

    @ManyToOne()
    @JoinColumn(name = "showroom_id")
    private Showroom showroom;

}
