package com.example.showroommanagement.service;

import com.example.showroommanagement.entity.Customer;
import com.example.showroommanagement.entity.Product;
import com.example.showroommanagement.repository.CustomerRepository;
import com.example.showroommanagement.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(final Product product ) {
        return this.productRepository.save(product);
    }

    public Product retrieveProductById(final Integer id) {
        return this.productRepository.findById(id).orElse(null);

    }

    public List<Product> retrieveCustomer() {
        return this.productRepository.findAll();
    }

    @Transactional
    public Product updateProductById( final Product product, Integer id) {
        final Product existingProduct = this.productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Data not found"));
        if (product.getId() != null) {
            existingProduct.setId(product.getId());
        }
        if (product.getModel() != null) {
            existingProduct.setModel(product.getModel());
        }
        if (product. getEmployee() != null) {
            existingProduct.setEmployee(product. getEmployee());
        }
        if (product.getPrice() != null) {
            existingProduct.setPrice(product.getPrice());
        }
        if (product.getColour() != null) {
            existingProduct.setColour(product.getColour());
        }
        if (product. getCustomer() != null) {
            existingProduct.setCustomer(product. getCustomer());
        }
        return this.productRepository.save(existingProduct);
    }

    @Transactional
    public Map<String, String> removeProductById(final Integer id) {
        this.productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));
        this.productRepository.deleteById(id);
        return (Map.of("message", "Product deleted successfully."));
    }

}
