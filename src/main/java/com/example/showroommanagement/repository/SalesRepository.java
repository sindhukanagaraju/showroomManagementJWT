package com.example.showroommanagement.repository;

import com.example.showroommanagement.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Sales,Integer> {
}
