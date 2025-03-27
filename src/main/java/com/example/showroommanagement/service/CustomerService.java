package com.example.showroommanagement.service;

import com.example.showroommanagement.dto.CustomerDetailDTO;
import com.example.showroommanagement.entity.Branch;
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

    public CustomerService(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
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
    public Customer updateCustomerById(final Customer customer, final Integer id) {
        final Customer existingCustomer = this.customerRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
        if (customer.getId() != null) {
            existingCustomer.setId(customer.getId());
        }
        if (customer.getName() != null) {
            existingCustomer.setName(customer.getName());
        }
        if (customer.getEmployee() != null) {
            existingCustomer.setEmployee(customer.getEmployee());
        }
        if (customer.getAddress() != null) {
            existingCustomer.setAddress(customer.getAddress());
        }
//        if (customer.getUser() != null) {
//            existingCustomer.setUser(customer.getUser());
//        }
        return this.customerRepository.save(existingCustomer);
    }

    public Customer removeCustomerById(final Integer id) {
        Customer customer = this.customerRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
        this.customerRepository.deleteById(id);
        return customer;
    }

    public List<CustomerDetailDTO> retrieveCustomerDetail() {
        List<Customer> retrieveCustomer = this.customerRepository.findAll();
        List<CustomerDetailDTO> customerDetailDTOS = new ArrayList<>();
        for (Customer customer : retrieveCustomer) {
            CustomerDetailDTO customerDetailDTO = new CustomerDetailDTO();
            customerDetailDTO.setName(customer.getName());
            customerDetailDTO.setCustomerAddress(customer.getAddress());
            customerDetailDTO.setShowroomName(customer.getEmployee().getDepartment().getShowroom().getName());
//            customerDetailDTO.setUserEmail(customer.getUser().getEmail());
            customerDetailDTOS.add(customerDetailDTO);
        }
        return customerDetailDTOS;
    }
    public long retrieveCustomerCount() {
        return this.customerRepository.count();
    }

    public List<String> retrieveCustomerName() {
        return customerRepository.findAllCustomerName();
    }
}


