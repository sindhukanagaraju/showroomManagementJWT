package com.example.showroommanagement.repository;

import com.example.showroommanagement.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(value = "SELECT name FROM customer", nativeQuery = true)
    List<String> findAllCustomerName();
}
