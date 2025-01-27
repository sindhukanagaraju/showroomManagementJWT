package com.example.showroommanagement.controller;

import com.example.showroommanagement.dto.CustomerDetailsDTO;
import com.example.showroommanagement.dto.ResponseDTO;
import com.example.showroommanagement.entity.Customer;
import com.example.showroommanagement.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public ResponseDTO createCustomer(@RequestBody final Customer customer) {
        return this.customerService.createCustomer(customer);
    }

    @GetMapping("/retrieve/{id}")
    public ResponseDTO retrieveCustomerById(@PathVariable final Integer id) {
        return this.customerService.retrieveDepartmentById(id);
    }

    @GetMapping("/retrieve")
    public ResponseDTO retrieveCustomer() {
        return this.customerService.retrieveCustomer();
    }

    @PutMapping("/update/{id}")
    public ResponseDTO updateCustomerById(@PathVariable final Integer id, @RequestBody final Customer customer) {
        return this.customerService.updateCustomerById(customer, id);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseDTO removeCustomerById(@PathVariable("id") final Integer id) {
        return this.customerService.removeCustomerById(id);
    }

    @GetMapping("/customerdetails")
    public ResponseDTO retrieveCustomerDetails() {
        return this.customerService.retrieveCustomerDetails();
    }


}
