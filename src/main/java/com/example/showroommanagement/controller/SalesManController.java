package com.example.showroommanagement.controller;

import com.example.showroommanagement.dto.ResponseDTO;
import com.example.showroommanagement.dto.SalesManDetailsDTO;
import com.example.showroommanagement.entity.SalesMan;
import com.example.showroommanagement.service.SalesManService;
import com.example.showroommanagement.util.Constant;
import org.springframework.http.HttpStatus;
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
        return new ResponseDTO(HttpStatus.OK.value(), this.salesManService.createSalesMan(salesMan), Constant.CREATE);

    }

    @GetMapping("/retrieve/{id}")
    public ResponseDTO retrieveSalesManById(@PathVariable final Integer id) {
        return new ResponseDTO(HttpStatus.OK.value(), this.salesManService.retrieveSalesManById(id), Constant.RETRIEVE);

    }

    @GetMapping("/retrieve")
    public ResponseDTO retrieveSalesMan() {
        return new ResponseDTO(HttpStatus.OK.value(), this.salesManService.retrieveSalesMan(), Constant.RETRIEVE);
    }

    @PutMapping("/update/{id}")
    public ResponseDTO updateSalesManById(@PathVariable final Integer id, @RequestBody final SalesMan salesMan) {
        return new ResponseDTO(HttpStatus.OK.value(), this.salesManService.updateSalesManById(salesMan, id), Constant.UPDATE);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseDTO removeSalesManById(@PathVariable("id") final Integer id) {
        return new ResponseDTO(HttpStatus.OK.value(), this.salesManService.removeSalesManById(id), Constant.REMOVE);
    }

    @GetMapping("/salesmandetails")
    public List<SalesManDetailsDTO> retrieveSalesmanDetails() {
        return this.salesManService.retrieveSalesManDetails();
    }


}
