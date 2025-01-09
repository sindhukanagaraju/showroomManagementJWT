package com.example.showroommanagement.entity;

import jakarta.persistence.*;
@Entity
@Table(name="department")
public class Department {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="id")
        private Integer id;
        @Column(name="name")
        private String name;
        @ManyToOne()
        @JoinColumn(name ="vivobranch_id")
        private VivoBranch vivoBranch;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VivoBranch getVivoBranch() {
        return vivoBranch;
    }

    public void setVivoBranch(VivoBranch vivoBranch) {
        this.vivoBranch = vivoBranch;
    }
}

