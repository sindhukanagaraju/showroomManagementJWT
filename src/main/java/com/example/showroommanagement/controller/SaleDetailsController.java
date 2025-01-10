package com.example.showroommanagement.controller;

import com.example.showroommanagement.entity.SaleDetails;
import com.example.showroommanagement.service.SaleDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/sales")
public class SaleDetailsController {


    private final SaleDetailsService saleDetailsService;

    public SaleDetailsController(SaleDetailsService saleDetailsService) {
        this.saleDetailsService = saleDetailsService;
    }

    @PostMapping("/create")
    public SaleDetails createSales(@RequestBody final SaleDetails salesDetails) {
        return this.saleDetailsService.createSales(salesDetails);
    }

    @GetMapping("/retrieve/{id}")
    public SaleDetails retrieveSalesById(@PathVariable final Integer id) {
        return this.saleDetailsService.retrieveSalesById(id);
    }

    @GetMapping("/retrieve")
    public List<SaleDetails> retrieveSales() {
        return this.saleDetailsService.retrieveSales();
    }

    @PutMapping("/update/{id}")
    public SaleDetails updateSalesById(@PathVariable final Integer id, @RequestBody final SaleDetails salesDetails) {
        return this.saleDetailsService.updateSalesById(salesDetails, id);
    }

    @DeleteMapping("/remove/{id}")
    public Map<String, String> removeSalesById(@PathVariable("id") final Integer id) {
        return this.saleDetailsService.removeSalesById(id);
    }


}
