package com.example.showroommanagement.service;

import com.example.showroommanagement.dto.CustomerDetailsDTO;
import com.example.showroommanagement.entity.Customer;
import com.example.showroommanagement.exception.BadRequestServiceAlertException;
import com.example.showroommanagement.repository.CustomerRepository;
import com.example.showroommanagement.util.Constant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
        return this.customerRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
    }

    public List<Customer> retrieveCustomer() {
        return this.customerRepository.findAll();
    }

    @Transactional
    public Customer updateCustomerById(final Customer customer, Integer id) {
        final Customer existingCustomer = this.customerRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
        if (customer.getId() != null) {
            existingCustomer.setId(customer.getId());
        }
        if (customer.getName() != null) {
            existingCustomer.setName(customer.getName());
        }
        if (customer.getSalesMan() != null) {
            existingCustomer.setSalesMan(customer.getSalesMan());
        }
        if (customer.getAddress() != null) {
            existingCustomer.setAddress(customer.getAddress());
        }
        return this.customerRepository.save(existingCustomer);
    }

    @Transactional
    public Customer removeCustomerById(final Integer id) {
        if (id == null) {
            throw new BadRequestServiceAlertException(Constant.DATA_NULL);
        }
        Customer customer = this.customerRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
        this.customerRepository.deleteById(id);
        return customer;
    }


    @Transactional
    public List<CustomerDetailsDTO> retrieveCustomerDetails() {
        List<Customer> retrieveCustomer = this.customerRepository.findAll();
        List<CustomerDetailsDTO> customerDetailsDTOS = new ArrayList<>();
        for (Customer customer : retrieveCustomer) {
            CustomerDetailsDTO customerDetailsDTO = new CustomerDetailsDTO();
            customerDetailsDTO.setCustomerName(customer.getName());
            customerDetailsDTO.setCustomerAddress(customer.getAddress());
            customerDetailsDTO.setSalesManName(customer.getSalesMan().getName());
            customerDetailsDTO.setShowroomName(customer.getSalesMan().getShowroom().getName());
            customerDetailsDTO.setShowroomContactNumber(customer.getSalesMan().getShowroom().getContactNumber());
            customerDetailsDTOS.add(customerDetailsDTO);
        }
        return customerDetailsDTOS;
    }

    public long count() {
        return this.customerRepository.count();
    }

    public List<String> getAllCustomerNames() {
        return customerRepository.findAllCustomerNames();
    }

}


