package com.example.showroommanagement.controller;

import com.example.showroommanagement.dto.ResponseDTO;
import com.example.showroommanagement.dto.SalesDetailsDTO;
import com.example.showroommanagement.entity.SaleDetails;
import com.example.showroommanagement.service.SaleDetailsService;
import com.example.showroommanagement.util.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sales")
public class SaleDetailsController {


    private final SaleDetailsService saleDetailsService;

    public SaleDetailsController(SaleDetailsService saleDetailsService) {
        this.saleDetailsService = saleDetailsService;
    }

    @PostMapping("/create")
    public ResponseDTO createSales(@RequestBody final SaleDetails salesDetails) {
        return new ResponseDTO(HttpStatus.OK.value(), this.saleDetailsService.createSales(salesDetails), Constant.CREATE);

    }

    @GetMapping("/retrieve/{id}")
    public ResponseDTO retrieveSalesById(@PathVariable final Integer id) {
        return new ResponseDTO(HttpStatus.OK.value(), this.saleDetailsService.retrieveSalesById(id), Constant.RETRIEVE);
    }

    @GetMapping("/retrieve")
    public ResponseDTO retrieveSales() {
        return new ResponseDTO(HttpStatus.OK.value(), this.saleDetailsService.retrieveSales(), Constant.RETRIEVE);
    }

    @PutMapping("/update/{id}")
    public ResponseDTO updateSalesById(@PathVariable final Integer id, @RequestBody final SaleDetails salesDetails) {
        return new ResponseDTO(HttpStatus.OK.value(), this.saleDetailsService.updateSalesById(salesDetails, id), Constant.UPDATE);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseDTO removeSalesById(@PathVariable("id") final Integer id) {
        return new ResponseDTO(HttpStatus.OK.value(), this.saleDetailsService.removeSalesById(id), Constant.REMOVE);
    }

    @GetMapping("/salesdetails")
    public List<SalesDetailsDTO> retrieveSalesDetails(@RequestParam final String showroomName, @RequestParam final String productModel) {
        return this.saleDetailsService.retrieveSalesDetails(showroomName, productModel);
    }
}
