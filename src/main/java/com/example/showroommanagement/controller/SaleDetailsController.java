package com.example.showroommanagement.controller;

import com.example.showroommanagement.dto.ResponseDTO;
import com.example.showroommanagement.entity.SaleDetails;
import com.example.showroommanagement.service.SaleDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sales")
public class SaleDetailsController {


    private final SaleDetailsService saleDetailsService;

    public SaleDetailsController(SaleDetailsService saleDetailsService) {
        this.saleDetailsService = saleDetailsService;
    }

    @PostMapping("/create")
    public ResponseDTO createSales(@RequestBody final SaleDetails salesDetails) {
        return this.saleDetailsService.createSales(salesDetails);
    }

    @GetMapping("/retrieve/{id}")
    public ResponseDTO retrieveSalesById(@PathVariable final Integer id) {
        return this.saleDetailsService.retrieveSalesById(id);
    }

    @GetMapping("/retrieve")
    public ResponseDTO retrieveSales() {
        return this.saleDetailsService.retrieveSales();
    }

    @PutMapping("/update/{id}")
    public ResponseDTO updateSalesById(@PathVariable final Integer id, @RequestBody final SaleDetails salesDetails) {
        return this.saleDetailsService.updateSalesById(salesDetails, id);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseDTO removeSalesById(@PathVariable("id") final Integer id) {
        return this.saleDetailsService.removeSalesById(id);
    }


    @GetMapping("/salesdetails")
    public ResponseDTO retrieveSalesDetails(@RequestParam final String showroomName, @RequestParam final String productModel) {
        return this.saleDetailsService.retrieveSalesDetails(showroomName, productModel);
    }


}
