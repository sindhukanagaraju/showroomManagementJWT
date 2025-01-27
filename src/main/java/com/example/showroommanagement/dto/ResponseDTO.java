package com.example.showroommanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {
    private Integer statusCode;
    private Object data;
    private String message;


}
