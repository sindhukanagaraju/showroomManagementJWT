package com.example.showroommanagement.service;

import com.example.showroommanagement.dto.ResponseDTO;
import com.example.showroommanagement.entity.Product;
import com.example.showroommanagement.exception.BadRequestServiceAlertException;
import com.example.showroommanagement.repository.ProductRepository;
import com.example.showroommanagement.util.Constant;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ResponseDTO createProduct(final Product product) {
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(this.productRepository.save(product));
        responseDTO.setMessage(Constant.CREATE);
        return responseDTO;
    }

    public ResponseDTO retrieveProductById(final Integer id) {
        if (this.productRepository.existsById(id)) {
            this.productRepository.findById(id);
            final ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setStatusCode(HttpStatus.OK.value());
            responseDTO.setData(this.productRepository.findById(id));
            responseDTO.setMessage(Constant.RETRIEVE);
            return responseDTO;
        } else {
            throw new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST);
        }
    }


    public ResponseDTO retrieveProduct() {
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.RETRIEVE);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(this.productRepository.findAll());
        return responseDTO;
    }

    @Transactional
    public ResponseDTO updateProductById(final Product product, Integer id) {
        final Product existingProduct = this.productRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
        if (product.getId() != null) {
            existingProduct.setId(product.getId());
        }
        if (product.getModel() != null) {
            existingProduct.setModel(product.getModel());
        }
        if (product.getSalesMan() != null) {
            existingProduct.setSalesMan(product.getSalesMan());
        }
        if (product.getPrice() != null) {
            existingProduct.setPrice(product.getPrice());
        }
        if (product.getColour() != null) {
            existingProduct.setColour(product.getColour());
        }
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.UPDATE);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(this.productRepository.save(existingProduct));
        return responseDTO;
    }

    public ResponseDTO removeProductById(final Integer id) {
        if (id == null) {
            throw new BadRequestServiceAlertException(Constant.DATA_NULL);
        }
        if (this.productRepository.existsById(id)) {
            this.productRepository.deleteById(id);
            final ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage(Constant.DELETE);
            responseDTO.setStatusCode(HttpStatus.OK.value());
            responseDTO.setData(Constant.REMOVE);
            return responseDTO;
        } else {
            throw new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST);
        }
    }
}


