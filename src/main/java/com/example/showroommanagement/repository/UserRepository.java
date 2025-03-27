package com.example.showroommanagement.repository;

import com.example.showroommanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

//    Optional<User> findByEmailAndPassword(String email, String password);
}
