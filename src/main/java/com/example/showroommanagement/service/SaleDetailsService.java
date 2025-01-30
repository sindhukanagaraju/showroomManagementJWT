package com.example.showroommanagement.service;

import com.example.showroommanagement.dto.SalesDetailsDTO;
import com.example.showroommanagement.entity.SaleDetails;
import com.example.showroommanagement.exception.BadRequestServiceAlertException;
import com.example.showroommanagement.repository.SaleDetailsRepository;
import com.example.showroommanagement.util.Constant;
import jakarta.transaction.Transactional;
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
    public SaleDetails createSales(final SaleDetails salesDetails) {
        return this.saleDetailsRepository.save(salesDetails);
    }


    public SaleDetails retrieveSalesById(final Integer id) {
        return this.saleDetailsRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
    }


    public List<SaleDetails> retrieveSales() {

        return this.saleDetailsRepository.findAll();

    }

    @Transactional
    public SaleDetails updateSalesById(final SaleDetails salesDetails, Integer id) {
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
        return this.saleDetailsRepository.save(existingSalesDetails);

    }

    @Transactional
    public SaleDetails removeSalesById(final Integer id) {
        if (id == null) {
            throw new BadRequestServiceAlertException(Constant.DATA_NULL);
        }
        SaleDetails saleDetails = this.saleDetailsRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
        this.saleDetailsRepository.deleteById(id);
        return saleDetails;
    }


    @Transactional
    public List<SalesDetailsDTO> retrieveSalesDetails(final String showroomName, final String bikeName) {
        List<SaleDetails> retrieveSales = this.saleDetailsRepository.findAll();
        List<SalesDetailsDTO> salesDetailsDTOS = new ArrayList<>();
        for (SaleDetails salesDetails : retrieveSales) {
            SalesDetailsDTO salesDetailsDTO = new SalesDetailsDTO();
            salesDetailsDTO.setShowroomName(salesDetails.getProduct().getSalesMan().getShowroom().getName());
            salesDetailsDTO.setShowroomBrand(salesDetails.getProduct().getSalesMan().getShowroom().getBrand());
            salesDetailsDTO.setProductModel(salesDetails.getProduct().getModel());
            salesDetailsDTO.setProductPrice(salesDetails.getProduct().getPrice());
            salesDetailsDTO.setSalesmanName(salesDetails.getProduct().getSalesMan().getName());
            salesDetailsDTO.setSalesDate(salesDetails.getSalesDate());
            salesDetailsDTO.setSalesManagerName(salesDetails.getProduct().getSalesMan().getShowroom().getManager().getName());
            salesDetailsDTO.setCustomerName(salesDetails.getCustomer().getName());
            salesDetailsDTO.setCustomerAddress(salesDetails.getCustomer().getAddress());
            salesDetailsDTOS.add(salesDetailsDTO);
        }
        return salesDetailsDTOS;

    }


}
