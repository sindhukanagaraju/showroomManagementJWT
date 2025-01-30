package com.example.showroommanagement.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ResponseDTO {
    private Integer statusCode;
    private Object data;
    private String message;

    public ResponseDTO(final Integer statusCode, final Object data, final String message) {
        this.statusCode = statusCode;
        this.data = data;
        this.message = message;
    }
}
