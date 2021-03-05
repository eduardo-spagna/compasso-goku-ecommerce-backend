package com.compassouol.gokuecommerce.dtos.response.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponseDTO {
    private String type;
    private String token;
}
