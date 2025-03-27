package com.example.showroommanagement.repository;

import com.example.showroommanagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query(value = "SELECT name, COUNT(id) as count from employee GROUP BY name ", nativeQuery = true)
    List<String> countOfName();
}
