package com.example.showroommanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="sales_department")
public class SalesDepartment {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="id")
        private Integer id;
        @Column(name="name")
        private String name;
    @ManyToOne()
    @JoinColumn(name ="vivo_showroom_id")
    private VivoShowroom vivoShowroom;


}

