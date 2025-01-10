package com.example.showroommanagement.repository;

import com.example.showroommanagement.entity.SalesMan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesManRepository extends JpaRepository<SalesMan, Integer> {
}
