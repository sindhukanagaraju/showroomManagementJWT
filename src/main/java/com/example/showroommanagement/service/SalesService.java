package com.example.showroommanagement.service;

import com.example.showroommanagement.entity.Sales;
import com.example.showroommanagement.repository.SalesRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class SalesService {
    private final SalesRepository salesRepository;

    public SalesService(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    public Sales createSales(final Sales sales) {

        return this.salesRepository.save(sales);
    }

    public Sales retrieveSalesById(final Integer id) {
        return this.salesRepository.findById(id).orElse(null);

    }

    public List<Sales> retrieveSales() {
        return this.salesRepository.findAll();
    }

    @Transactional
    public Sales updateSalesById( final Sales sales, Integer id) {
        final Sales existingSales = this.salesRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Data not found"));
        if (sales.getId() != null) {
            existingSales.setId(sales.getId());
        }
        if (sales.getTransaction() != null) {
            existingSales.setTransaction(sales.getTransaction());
        }
        if (sales. getProduct() != null) {
            existingSales.setProduct(sales. getProduct());
        }
        if (sales. getCustomer() != null) {
            existingSales.setCustomer(sales. getCustomer());
        }
        return this.salesRepository.save(existingSales);
    }

    @Transactional
    public Map<String, String> removeSalesById(final Integer id) {
        this.salesRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Customer not found with id: " + id));
        this.salesRepository.deleteById(id);
        return (Map.of("message", "Customer deleted successfully."));
    }

}
