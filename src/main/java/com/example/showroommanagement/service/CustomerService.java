package com.example.showroommanagement.service;

import com.example.showroommanagement.dto.CustomerDetailsDTO;
import com.example.showroommanagement.dto.ResponseDTO;
import com.example.showroommanagement.entity.Customer;
import com.example.showroommanagement.exception.BadRequestServiceAlertException;
import com.example.showroommanagement.repository.CustomerRepository;
import com.example.showroommanagement.util.Constant;
import org.springframework.http.HttpStatus;
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

    public ResponseDTO createCustomer(final Customer customer) {
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.CREATE);
        responseDTO.setStatusCode(HttpStatus.CREATED.value());
        responseDTO.setData(this.customerRepository.save(customer));
        return responseDTO;
    }
    public ResponseDTO retrieveDepartmentById(final Integer id) {
        if (this.customerRepository.existsById(id)) {
            this.customerRepository.findById(id)
            ;
            final ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage(Constant.RETRIEVE);
            responseDTO.setStatusCode(HttpStatus.OK.value());
            responseDTO.setData(this.customerRepository.findById(id));
            return responseDTO;
        } else {
            throw new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST);
        }
    }
    public ResponseDTO retrieveCustomer() {
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.RETRIEVE);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(this.customerRepository.findAll());
        return responseDTO;
    }

    public ResponseDTO updateCustomerById(final Customer customer, Integer id) {
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
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.UPDATE);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(customerRepository.save(existingCustomer));
        return responseDTO;
    }
        @Transactional

        public ResponseDTO removeCustomerById ( final Integer id){
            if (id == null) {
                throw new BadRequestServiceAlertException(Constant.DATA_NULL);
            }
            if (this.customerRepository.existsById(id)) {
                final ResponseDTO responseDTO = new ResponseDTO();
                responseDTO.setMessage(Constant.DELETE);
                responseDTO.setStatusCode(HttpStatus.OK.value());
                responseDTO.setData(Constant.REMOVE);
                return responseDTO;
            } else {
                throw new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST);
            }
    }

    @Transactional
    public ResponseDTO retrieveCustomerDetails() {
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
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.RETRIEVE);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(customerDetailsDTOS);
        return responseDTO;
    }



}


