package com.example.showroommanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="vivo_branch")
public class VivoBranch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="location")
    private String location;
   @ManyToOne()
   @JoinColumn(name ="showroom_id")
   private VivoShowrooms VivoShowrooms;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public VivoShowrooms getVivoShowrooms() {
        return VivoShowrooms;
    }

    public void setVivoShowrooms(VivoShowrooms VivoShowrooms) {
        this.VivoShowrooms = VivoShowrooms;
    }
}
