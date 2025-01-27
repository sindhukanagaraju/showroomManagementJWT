package com.example.showroommanagement.repository;

import com.example.showroommanagement.entity.SaleDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SaleDetailsRepository extends JpaRepository<SaleDetails,Integer> {
    @Query("SELECT sdetail FROM SaleDetails sdetail " +
            "JOIN sdetail.product p " +
            "JOIN p.salesMan slman " +
            "JOIN slman.showroom shrm " +
            "JOIN shrm.manager mngr " +
            "JOIN sdetail.customer cs " +
            "JOIN cs.salesMan sman " +
            "JOIN sman.showroom sroom " +
            "WHERE shrm.name = :showroomName " +
            "AND p.model = :productModel")
    List<SaleDetails> retrieveSalesDetails(@Param("showroomName") String showroomName,
                                           @Param("productModel") String productModel);

}
