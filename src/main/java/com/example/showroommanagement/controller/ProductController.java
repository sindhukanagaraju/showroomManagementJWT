package com.example.showroommanagement.controller;

import com.example.showroommanagement.entity.Product;
import com.example.showroommanagement.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public Product createProduct(@RequestBody final Product product) {
        return this.productService.createProduct(product);
    }

    @GetMapping("/retrieve/{id}")
    public Product retrieveProductById(@PathVariable final Integer id) {
        return this.productService.retrieveProductById(id);
    }

    @GetMapping("/retrieve")
    public List<Product> retrieveProduct() {
        return this.productService.retrieveCustomer();
    }

    @PutMapping("/update/{id}")
    public Product updateProductById(@PathVariable final Integer id, @RequestBody final Product product) {
        return this.productService.updateProductById(product, id);
    }

    @DeleteMapping("/remove/{id}")
    public Map<String, String> removeProductById(@PathVariable("id") final Integer id) {
        return this.productService. removeProductById(id);
    }


}
