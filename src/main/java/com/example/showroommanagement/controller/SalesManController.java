package com.example.showroommanagement.controller;

import com.example.showroommanagement.entity.SalesMan;
import com.example.showroommanagement.service.SalesManService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/employee")
public class SalesManController {

    private final SalesManService salesManService;

    public SalesManController(SalesManService salesManService) {
        this.salesManService = salesManService;
    }

    @PostMapping("/create")
    public SalesMan createSalesMan(@RequestBody final SalesMan salesMan) {
        return this.salesManService.createSalesMan(salesMan);
    }

    @GetMapping("/retrieve/{id}")
    public SalesMan retrieveSalesManById(@PathVariable final Integer id) {
        return this.salesManService.retrieveSalesManById(id);
    }

    @GetMapping("/retrieve")
    public List<SalesMan> retrieveSalesMan() {
        return this.salesManService.retrieveSalesMan();
    }

    @PutMapping("/update/{id}")
    public SalesMan updateSalesManById(@PathVariable final Integer id, @RequestBody final SalesMan salesMan) {
        return this.salesManService.updateSalesManById(salesMan, id);
    }

    @DeleteMapping("/remove/{id}")
    public Map<String, String> removeSalesManById(@PathVariable("id") final Integer id) {
        return this.salesManService.removeSalesManById(id);
    }


}
