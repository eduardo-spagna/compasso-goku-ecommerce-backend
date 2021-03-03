package com.compassouol.gokuecommerce.dtos.request.user;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "O nome do usuário é obrigatório")
    @Size(max = 50, message = "O nome do usuário deve conter no máximo 50 caracteres")
    @ApiModelProperty(example = "Eduardo Spagna", required = true)
    private String userName;

    @NotBlank(message = "O email de login do usuário é obrigatório")
    @Size(max = 90, message = "O email de login do usuário deve conter no máximo 90 caracteres")
    @Email(message = "O email informado deve ser válido")
    @ApiModelProperty(example = "eduardo@email.com", required = true)
    private String userEmail;

    @NotBlank(message = "A senha de login do usuário é obrigatória")
    @Size(min = 8, max = 20, message = "A senha de login do usuário deve conter no mínimo 8 e no máximo 20 caracteres")
    @ApiModelProperty(example = "12345678", required = true)
    private String userPassword;
}
