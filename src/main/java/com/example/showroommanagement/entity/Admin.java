package com.example.showroommanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

//    @ManyToOne
//    @JoinColumn(name = "user_id", unique = true)
//    private User user;
    @ManyToOne
    @JoinColumn(name = "showroom_id", unique = true)
    private Showroom showroom;
}
