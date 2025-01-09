package com.example.showroommanagement.controller;

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
    public Customer createCustomer(@RequestBody final  Customer  customer) {
        return this.customerService.createCustomer(customer);
    }

    @GetMapping("/retrieve/{id}")
    public Customer retrieveCustomerById(@PathVariable final Integer id) {
        return this.customerService.retrieveDepartmentById(id);
    }

    @GetMapping("/retrieve")
    public List<Customer> retrieveCustomer() {
        return this.customerService.retrieveCustomer();
    }

    @PutMapping("/update/{id}")
    public Customer updateCustomerById(@PathVariable final Integer id, @RequestBody final Customer customer) {
        return this.customerService.updateCustomerById(customer, id);
    }

    @DeleteMapping("/remove/{id}")
    public Map<String, String> removeCustomerById(@PathVariable("id") final Integer id) {
        return this.customerService. removeCustomerById(id);
    }



}
