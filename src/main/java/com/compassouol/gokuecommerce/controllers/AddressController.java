package com.compassouol.gokuecommerce.controllers;

import javax.validation.Valid;

import com.compassouol.gokuecommerce.dtos.request.address.CreateAddressRequestDTO;
import com.compassouol.gokuecommerce.dtos.response.ErrorResponseDTO;
import com.compassouol.gokuecommerce.dtos.response.ResponseDTO;
import com.compassouol.gokuecommerce.dtos.response.address.ShowAddressResponseDTO;
import com.compassouol.gokuecommerce.models.Address;
import com.compassouol.gokuecommerce.models.User;
import com.compassouol.gokuecommerce.services.AddressService;
import com.compassouol.gokuecommerce.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/v1/addresses")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @Autowired
    private UserService userService;

    @PostMapping
    @ApiOperation(value = "Create an address for the logged in user")
    public ResponseEntity<ResponseDTO<?>> store(@Valid @RequestBody CreateAddressRequestDTO createAddress,
            BindingResult result, @RequestHeader(value = "Authorization") String authorization) {
        try {
            if (result.hasErrors() == true) {
                ErrorResponseDTO<Object> errorResponseDTO = new ErrorResponseDTO<Object>(
                        result.getFieldError().getCode(), result.getFieldError().getField(),
                        result.getFieldError().getDefaultMessage(), result.getFieldError().getRejectedValue());
                ResponseDTO<ErrorResponseDTO<Object>> response = new ResponseDTO<ErrorResponseDTO<Object>>(
                        "address/invalid-data", "Dados inválidos", errorResponseDTO);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String userEmail = auth.getName();
            User user = userService.findUserByEmail(userEmail);

            Address address = addressService.create(createAddress, user);

            ShowAddressResponseDTO response = new ShowAddressResponseDTO(address.getAddressId(),
                    address.getAddressZipCode(), address.getAddressStreet(), address.getAddressStreetNumber(),
                    address.getAddressDistrict(), address.getAddressComplement(), address.getAddressCity(),
                    address.getAddressState());

            ResponseDTO<ShowAddressResponseDTO> responseDTO = new ResponseDTO<ShowAddressResponseDTO>(
                    "address/successfully-created", "Endereço criado com sucesso", response);

            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (Exception e) {
            System.err.println("Erro ao criar o endereco: " + e.getMessage());
            ResponseDTO<ShowAddressResponseDTO> responseDTO = new ResponseDTO<ShowAddressResponseDTO>(
                    "address/error-creating", "Ocorreu um erro ao criar o endereco", null);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
    }
}
