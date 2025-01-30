package com.example.showroommanagement.controller;

import com.example.showroommanagement.dto.ResponseDTO;
import com.example.showroommanagement.entity.Customer;
import com.example.showroommanagement.service.CustomerService;
import com.example.showroommanagement.util.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public ResponseDTO createCustomer(@RequestBody final Customer customer) {
        return new ResponseDTO(HttpStatus.OK.value(), this.customerService.createCustomer(customer), Constant.CREATE);
    }

    @GetMapping("/retrieve/{id}")
    public ResponseDTO retrieveCustomerById(@PathVariable final Integer id) {
        return new ResponseDTO(HttpStatus.OK.value(), this.customerService.retrieveDepartmentById(id), Constant.RETRIEVE);
    }

    @GetMapping("/retrieve")
    public ResponseDTO retrieveCustomer() {
        return new ResponseDTO(HttpStatus.OK.value(), this.customerService.retrieveCustomer(), Constant.RETRIEVE);
    }

    @PutMapping("/update/{id}")
    public ResponseDTO updateCustomerById(@PathVariable final Integer id, @RequestBody final Customer customer) {
        return new ResponseDTO(HttpStatus.OK.value(), this.customerService.updateCustomerById(customer, id), Constant.UPDATE);

    }

    @DeleteMapping("/remove/{id}")
    public ResponseDTO removeCustomerById(@PathVariable("id") final Integer id) {
        return new ResponseDTO(HttpStatus.OK.value(), this.customerService.removeCustomerById(id), Constant.REMOVE);
    }

    @GetMapping("/customerdetails")
    public ResponseDTO retrieveCustomerDetails() {
        return new ResponseDTO(HttpStatus.OK.value(), this.customerService.retrieveCustomerDetails(), Constant.RETRIEVE);
    }

    @GetMapping("/count")
    public long getCustomerCount() {
        return this.customerService.count();
    }

    @GetMapping("/name")

    public List<String> getAllCustomerNames() {
        return this.customerService.getAllCustomerNames();
    }


}
