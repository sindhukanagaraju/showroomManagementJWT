package com.example.showroommanagement.controller;

import com.example.showroommanagement.dto.ResponseDTO;
import com.example.showroommanagement.dto.SalesManDetailsDTO;
import com.example.showroommanagement.entity.SalesMan;
import com.example.showroommanagement.service.SalesManService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/salesman")
public class SalesManController {

    private final SalesManService salesManService;

    public SalesManController(SalesManService salesManService) {
        this.salesManService = salesManService;
    }

    @PostMapping("/create")
    public ResponseDTO createSalesMan(@RequestBody final SalesMan salesMan) {
        return this.salesManService.createSalesMan(salesMan);
    }

    @GetMapping("/retrieve/{id}")
    public ResponseDTO retrieveSalesManById(@PathVariable final Integer id) {
        return this.salesManService.retrieveSalesManById(id);
    }

    @GetMapping("/retrieve")
    public ResponseDTO retrieveSalesMan() {
        return this.salesManService.retrieveSalesMan();
    }

    @PutMapping("/update/{id}")
    public ResponseDTO updateSalesManById(@PathVariable final Integer id, @RequestBody final SalesMan salesMan) {
        return this.salesManService.updateSalesManById(salesMan, id);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseDTO removeSalesManById(@PathVariable("id") final Integer id) {
        return this.salesManService.removeSalesManById(id);
    }

    @GetMapping("/salesmandetails")
    public List<SalesManDetailsDTO> retrieveSalesmanDetails() {
        return this.salesManService.retrieveSalesManDetails();
    }


}
