package com.compassouol.gokuecommerce.dtos.response.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShowUserResponseDTO {
    private long userId;
    private String userName;
    private String userEmail;
}
