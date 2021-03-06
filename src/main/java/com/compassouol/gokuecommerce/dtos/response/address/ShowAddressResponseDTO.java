package com.compassouol.gokuecommerce.dtos.response.address;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShowAddressResponseDTO {
    private long addressId;
    private String addressZipCode;
    private String addressStreet;
    private String addressStreetNumber;
    private String addressDistrict;
    private String addressComplement;
    private String addressCity;
    private String addressState;
}
