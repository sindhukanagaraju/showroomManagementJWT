package com.example.showroommanagement.repository;

import com.example.showroommanagement.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    long count();

    @Query(value = "SELECT name FROM customer", nativeQuery = true)
    List<String> findAllCustomerNames();


}
