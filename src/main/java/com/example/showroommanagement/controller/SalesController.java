package com.example.showroommanagement.controller;

import com.example.showroommanagement.entity.Sales;
import com.example.showroommanagement.service.SalesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/sales")
public class SalesController {


        private final SalesService salesService;

        public SalesController(SalesService salesService) {
            this.salesService = salesService;
        }

        @PostMapping("/create")
        public Sales createSales(@RequestBody final Sales sales) {
            return this.salesService.createSales(sales);
        }

        @GetMapping("/retrieve/{id}")
        public Sales retrieveSalesById(@PathVariable final Integer id) {
            return this.salesService.retrieveSalesById(id);
        }

        @GetMapping("/retrieve")
        public List<Sales> retrieveSales() {
            return this.salesService.retrieveSales();
        }

        @PutMapping("/update/{id}")
        public Sales updateSalesById(@PathVariable final Integer id, @RequestBody final Sales sales) {
            return this.salesService.updateSalesById(sales, id);
        }

        @DeleteMapping("/remove/{id}")
        public Map<String, String> removeSalesById(@PathVariable("id") final Integer id) {
            return this.salesService. removeSalesById(id);
        }


    }
