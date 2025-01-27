package com.example.showroommanagement.controller;

import com.example.showroommanagement.dto.ResponseDTO;
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
    public ResponseDTO createProduct(@RequestBody final Product product) {
        return this.productService.createProduct(product);
    }

    @GetMapping("/retrieve/{id}")
    public ResponseDTO retrieveProductById(@PathVariable final Integer id) {
        return this.productService.retrieveProductById(id);
    }

    @GetMapping("/retrieve")
    public ResponseDTO retrieveProduct() {
        return this.productService.retrieveProduct();
    }

    @PutMapping("/update/{id}")
    public ResponseDTO updateProductById(@PathVariable final Integer id, @RequestBody final Product product) {
        return this.productService.updateProductById(product,id);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseDTO removeProductById(@PathVariable("id") final Integer id) {
        return this.productService.removeProductById(id);
    }


}
