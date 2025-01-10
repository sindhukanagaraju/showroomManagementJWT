package com.example.showroommanagement.repository;

import com.example.showroommanagement.entity.VivoShowroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VivoShowroomRepository extends JpaRepository<VivoShowroom, Integer> {

}
