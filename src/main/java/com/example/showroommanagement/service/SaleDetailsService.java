package com.example.showroommanagement.service;

import com.example.showroommanagement.entity.SaleDetails;
import com.example.showroommanagement.repository.SaleDetailsRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class SaleDetailsService {
    private final SaleDetailsRepository saleDetailsRepository;

    public SaleDetailsService(SaleDetailsRepository saleDetailsRepository) {
        this.saleDetailsRepository = saleDetailsRepository;
    }

    public SaleDetails createSales(final SaleDetails salesDetails) {

        return this.saleDetailsRepository.save(salesDetails);
    }

    public SaleDetails retrieveSalesById(final Integer id) {
        return this.saleDetailsRepository.findById(id).orElse(null);

    }

    public List<SaleDetails> retrieveSales() {
        return this.saleDetailsRepository.findAll();
    }

    @Transactional
    public SaleDetails updateSalesById(final SaleDetails salesDetails, Integer id) {
        final SaleDetails existingSalesDetails = this.saleDetailsRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Data not found"));
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
    public Map<String, String> removeSalesById(final Integer id) {
        this.saleDetailsRepository.findById(id).orElseThrow(() -> new NoSuchElementException("saledetails not found with id: " + id));
        this.saleDetailsRepository.deleteById(id);
        return (Map.of("message", "saledatails deleted successfully."));
    }

}
