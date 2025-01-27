package com.example.showroommanagement.service;

import com.example.showroommanagement.dto.ResponseDTO;
import com.example.showroommanagement.dto.SalesManDetailsDTO;
import com.example.showroommanagement.entity.SalesMan;
import com.example.showroommanagement.exception.BadRequestServiceAlertException;
import com.example.showroommanagement.repository.SalesManRepository;
import com.example.showroommanagement.util.Constant;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalesManService {

    private final SalesManRepository salesManRepository;

    public SalesManService(SalesManRepository salesManRepository) {
        this.salesManRepository = salesManRepository;
    }

    @Transactional
    public ResponseDTO createSalesMan(final SalesMan salesman) {
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.CREATE);
        responseDTO.setStatusCode(HttpStatus.CREATED.value());
        responseDTO.setData(this.salesManRepository.save(salesman));
        return responseDTO;
    }

    public ResponseDTO retrieveSalesManById(final Integer id) {
        if (this.salesManRepository.existsById(id)) {
            this.salesManRepository.findById(id);
            final ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage(Constant.RETRIEVE);
            responseDTO.setStatusCode(HttpStatus.OK.value());
            responseDTO.setData(this.salesManRepository.findById(id));
            return responseDTO;
        } else {
            throw new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST);
        }
    }

    public ResponseDTO retrieveSalesMan() {
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.RETRIEVE);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(this.salesManRepository.findAll());
        return responseDTO;
    }

    @Transactional
    public ResponseDTO updateSalesManById(final SalesMan salesMan, Integer id) {
        final SalesMan existingSalesMan = this.salesManRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
        if (salesMan.getId() != null) {
            existingSalesMan.setId(salesMan.getId());
        }
        if (salesMan.getName() != null) {
            existingSalesMan.setName(salesMan.getName());
        }
        if (salesMan.getSalary() != null) {
            existingSalesMan.setSalary(salesMan.getSalary());
        }
        if (salesMan.getAddress() != null) {
            existingSalesMan.setAddress(salesMan.getAddress());
        }
        if (salesMan.getShowroom() != null) {
            existingSalesMan.setShowroom(salesMan.getShowroom());
        }

        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.UPDATE);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(this.salesManRepository.save(existingSalesMan));
        return responseDTO;

    }

    @Transactional
    public ResponseDTO removeSalesManById(final Integer id) {
        if (id == null) {
            throw new BadRequestServiceAlertException(Constant.DATA_NULL);
        }
        if (this.salesManRepository.existsById(id)) {
            this.salesManRepository.deleteById(id);
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
    public List<SalesManDetailsDTO> retrieveSalesManDetails() {
        List<SalesMan> retrieveSalesMan = this.salesManRepository.findAll();
        List<SalesManDetailsDTO> salesManDetailsDTOS = new ArrayList<>();
        for (SalesMan salesMan : retrieveSalesMan) {
            SalesManDetailsDTO salesManDetailsDTO = new SalesManDetailsDTO();
            salesManDetailsDTO.setSalesManName(salesMan.getName());
            salesManDetailsDTO.setSalesManSalary(salesMan.getSalary());
            salesManDetailsDTO.setManagerName(salesMan.getShowroom().getManager().getName());
            salesManDetailsDTO.setShowroomName(salesMan.getShowroom().getName());
            salesManDetailsDTO.setShowroomAddress(salesMan.getShowroom().getAddress());
            salesManDetailsDTO.setManagerPhoneNumber(salesMan.getShowroom().getManager().getContactNumber());
            salesManDetailsDTO.setShowroomPhoneNumber(salesMan.getShowroom().getContactNumber());
            salesManDetailsDTOS.add(salesManDetailsDTO);
        }
        return salesManDetailsDTOS;
    }
}
