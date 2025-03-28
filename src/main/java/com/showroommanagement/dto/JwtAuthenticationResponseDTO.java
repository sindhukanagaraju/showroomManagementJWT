package com.showroommanagement.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponseDTO {
    private String token;
    private String refreshToken;
}
