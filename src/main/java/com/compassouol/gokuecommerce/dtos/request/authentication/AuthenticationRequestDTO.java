package com.compassouol.gokuecommerce.dtos.request.authentication;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "O email de login do usuário é obrigatório")
    @Email(message = "O email informado deve ser válido")
    @ApiModelProperty(example = "eduardo@email.com", required = true)
    private String email;

    @NotBlank(message = "A senha de login do usuário é obrigatória")
    @ApiModelProperty(example = "12345678", required = true)
    private String password;
}
