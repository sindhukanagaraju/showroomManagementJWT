package com.example.showroommanagement.repository;

import com.example.showroommanagement.entity.SalesDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesDepartmentRepository extends JpaRepository<SalesDepartment,Integer> {
}
