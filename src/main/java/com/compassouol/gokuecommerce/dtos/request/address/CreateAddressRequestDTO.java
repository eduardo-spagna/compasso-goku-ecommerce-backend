package com.compassouol.gokuecommerce.dtos.request.address;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAddressRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "O CEP é obrigatório")
    @Pattern(regexp = "(^\\d{8}$)", message = "O CEP deve conter obrigatóriamente 8 caracteres numéricos")
    @ApiModelProperty(example = "86300000", required = true)
    private String addressZipCode;

    @NotBlank(message = "O nome da rua é obrigatório")
    @Size(max = 60, message = "O nome da rua deve conter no máximo 60 caracteres")
    @ApiModelProperty(example = "Av. Alberto Carazzai", required = true)
    private String addressStreet;

    @NotBlank(message = "O número da residência é obrigatório")
    @Size(max = 10, message = "O número da residência deve conter no máximo 10 caracteres")
    @ApiModelProperty(example = "890", required = true)
    private String addressStreetNumber;

    @NotBlank(message = "O nome do bairro é obrigatório")
    @Size(max = 60, message = "O nome do bairro deve conter no máximo 60 caracteres")
    @ApiModelProperty(example = "Centro", required = true)
    private String addressDistrict;

    @Size(max = 60, message = "O complemento deve conter no máximo 60 caracteres")
    @ApiModelProperty(example = "prox a UTFPR", required = false)
    private String addressComplement;

    @NotBlank(message = "O nome da cidade é obrigatório")
    @Size(max = 60, message = "O nome da cidade deve conter no máximo 50 caracteres")
    @ApiModelProperty(example = "Cornélio Procópio", required = true)
    private String addressCity;

    @NotBlank(message = "A sigla do estado é obrigatória")
    @Size(min = 2, max = 2, message = "A sigla do estado deve conter 2 caracteres")
    @ApiModelProperty(example = "PR", required = true)
    private String addressState;
}
