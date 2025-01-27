package com.example.showroommanagement.service;

import com.example.showroommanagement.dto.ResponseDTO;
import com.example.showroommanagement.dto.SalesDetailsDTO;
import com.example.showroommanagement.entity.SaleDetails;
import com.example.showroommanagement.exception.BadRequestServiceAlertException;
import com.example.showroommanagement.repository.SaleDetailsRepository;
import com.example.showroommanagement.util.Constant;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleDetailsService {
    private final SaleDetailsRepository saleDetailsRepository;

    public SaleDetailsService(SaleDetailsRepository saleDetailsRepository) {
        this.saleDetailsRepository = saleDetailsRepository;
    }

    @Transactional
    public ResponseDTO createSales(final SaleDetails salesDetails) {
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.CREATE);
        responseDTO.setStatusCode(HttpStatus.CREATED.value());
        responseDTO.setData(this.saleDetailsRepository.save(salesDetails));
        return responseDTO;
    }


    public ResponseDTO retrieveSalesById(final Integer id) {
        if (this.saleDetailsRepository.existsById(id)) {
            final ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage(Constant.RETRIEVE);
            responseDTO.setStatusCode(HttpStatus.OK.value());
            responseDTO.setData(this.saleDetailsRepository.findById(id));
            return responseDTO;
        } else {
            throw new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST);
        }
    }

    public ResponseDTO retrieveSales() {
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.RETRIEVE);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(this.saleDetailsRepository.findAll());
        return responseDTO;
    }

    @Transactional
    public ResponseDTO updateSalesById(final SaleDetails salesDetails, Integer id) {
        final SaleDetails existingSalesDetails = this.saleDetailsRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
        if (salesDetails.getId() != null) {
            existingSalesDetails.setId(salesDetails.getId());
        }
        if (salesDetails.getSalesDate() != null) {
            existingSalesDetails.setSalesDate(salesDetails.getSalesDate());
        }
        if (salesDetails.getProduct() != null) {
            existingSalesDetails.setProduct(salesDetails.getProduct());
        }
        if (salesDetails.getCustomer() != null) {
            existingSalesDetails.setCustomer(salesDetails.getCustomer());
        }
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.UPDATE);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(this.saleDetailsRepository.save(existingSalesDetails));
        return responseDTO;
    }

    @Transactional
    public ResponseDTO removeSalesById(final Integer id) {
        if (id == null) {
            throw new BadRequestServiceAlertException(Constant.DATA_NULL);
        }
        if (this.saleDetailsRepository.existsById(id)) {
            this.saleDetailsRepository.deleteById(id);
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
    public ResponseDTO retrieveSalesDetails(final String showroomName, final String bikeName) {
        List<SaleDetails> retrieveSales = this.saleDetailsRepository.findAll();
        List<SalesDetailsDTO> salesDetailsDTOS = new ArrayList<>();
        for (SaleDetails salesDetails : retrieveSales) {
            SalesDetailsDTO salesDetailsDTO = new SalesDetailsDTO();
            salesDetailsDTO.setShowroomName(salesDetails.getProduct().getSalesMan().getShowroom().getName());
            salesDetailsDTO.setShowroomBrand(salesDetails.getProduct().getSalesMan().getShowroom().getBrand());
            salesDetailsDTO.setProductModel(salesDetails.getProduct().getModel());
            salesDetailsDTO.setProductprice(salesDetails.getProduct().getPrice());
            salesDetailsDTO.setSalesmanName(salesDetails.getProduct().getSalesMan().getName());
            salesDetailsDTO.setSalesDate(salesDetails.getSalesDate());
            salesDetailsDTO.setSalesManagerName(salesDetails.getProduct().getSalesMan().getShowroom().getManager().getName());
            salesDetailsDTO.setCustomerName(salesDetails.getCustomer().getName());
            salesDetailsDTO.setCustomerAddress(salesDetails.getCustomer().getAddress());
            salesDetailsDTOS.add(salesDetailsDTO);
        }
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.RETRIEVE);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(salesDetailsDTOS);
        return responseDTO;

    }

}
