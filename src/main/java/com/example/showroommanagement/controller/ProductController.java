package com.example.showroommanagement.controller;

import com.example.showroommanagement.dto.ResponseDTO;
import com.example.showroommanagement.entity.Product;
import com.example.showroommanagement.service.ProductService;
import com.example.showroommanagement.util.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseDTO createProduct(@RequestBody final Product product) {
        return new ResponseDTO(HttpStatus.OK.value(), this.productService.createProduct(product), Constant.CREATE);
    }

    @GetMapping("/retrieve/{id}")
    public ResponseDTO retrieveProductById(@PathVariable final Integer id) {
        return new ResponseDTO(HttpStatus.OK.value(), this.productService.retrieveProductById(id), Constant.RETRIEVE);
    }

    @GetMapping("/retrieve")
    public ResponseDTO retrieveProduct() {
        return new ResponseDTO(HttpStatus.OK.value(), this.productService.retrieveProduct(), Constant.RETRIEVE);
    }

    @PutMapping("/update/{id}")
    public ResponseDTO updateProductById(@PathVariable final Integer id, @RequestBody final Product product) {
        return new ResponseDTO(HttpStatus.OK.value(), this.productService.updateProductById(product, id), Constant.UPDATE);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseDTO removeProductById(@PathVariable("id") final Integer id) {
        return new ResponseDTO(HttpStatus.OK.value(), this.productService.removeProductById(id), Constant.REMOVE);
    }


}
