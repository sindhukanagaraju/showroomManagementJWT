package com.example.showroommanagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SalesDetailsDTO {
    private String showroomName;
    private String showroomBrand;
    private String productModel;
    private Double productPrice;
    private String salesmanName;
    private String salesManagerName;
    private String customerName;
    private String customerAddress;
    private Date salesDate;
}
