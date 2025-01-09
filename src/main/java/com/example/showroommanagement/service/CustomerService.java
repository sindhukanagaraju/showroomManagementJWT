package com.example.showroommanagement.service;

import com.example.showroommanagement.entity.Customer;
import com.example.showroommanagement.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(final Customer customer) {
        return this.customerRepository.save(customer);
    }

    public Customer retrieveDepartmentById(final Integer id) {
        return this.customerRepository.findById(id).orElse(null);

    }

    public List<Customer> retrieveCustomer() {
        return this.customerRepository.findAll();
    }

    @Transactional
    public Customer updateCustomerById( final Customer customer, Integer id) {
        final Customer existingCustomer = this.customerRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Data not found"));
        if (customer.getId() != null) {
            existingCustomer.setId(customer.getId());
        }
        if (customer.getName() != null) {
            existingCustomer.setName(customer.getName());
        }
        if (customer. getEmployee() != null) {
            existingCustomer.setEmployee(customer. getEmployee());
        }
        return this.customerRepository.save(existingCustomer);
    }

    @Transactional
    public Map<String, String> removeCustomerById(final Integer id) {
        this.customerRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Customer not found with id: " + id));
        this.customerRepository.deleteById(id);
        return (Map.of("message", "Customer deleted successfully."));
    }


}
