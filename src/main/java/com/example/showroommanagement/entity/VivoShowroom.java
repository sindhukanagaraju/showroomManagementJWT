package com.example.showroommanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "showroom")
public class VivoShowroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "contact_number")
    private String contactNumber;
    @ManyToOne()
    @JoinColumn(name = "manager_id")
    private Manager manager;
}
