package com.example.showroommanagement.repository;

import com.example.showroommanagement.entity.VivoShowrooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowroomRepository extends JpaRepository<VivoShowrooms,Integer> {

}
