package com.compassouol.gokuecommerce.controllers;

import javax.validation.Valid;

import com.compassouol.gokuecommerce.dtos.request.authentication.AuthenticationRequestDTO;
import com.compassouol.gokuecommerce.dtos.response.ErrorResponseDTO;
import com.compassouol.gokuecommerce.dtos.response.ResponseDTO;
import com.compassouol.gokuecommerce.dtos.response.authentication.AuthenticationResponseDTO;
import com.compassouol.gokuecommerce.exceptions.CustomException;
import com.compassouol.gokuecommerce.services.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/v1/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    @ApiOperation(value = "Authenticate an user")
    public ResponseEntity<ResponseDTO<?>> userAuthentication(@Valid @RequestBody AuthenticationRequestDTO authRequest,
            BindingResult result) {
        try {
            if (result.hasErrors() == true) {
                ErrorResponseDTO<Object> errorResponseDTO = new ErrorResponseDTO<Object>(
                        result.getFieldError().getCode(), result.getFieldError().getField(),
                        result.getFieldError().getDefaultMessage(), result.getFieldError().getRejectedValue());
                ResponseDTO<ErrorResponseDTO<Object>> response = new ResponseDTO<ErrorResponseDTO<Object>>(
                        "auth/invalid-data", "Dados inválidos", errorResponseDTO);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            String token = authenticationService.signin(authRequest.getEmail(), authRequest.getPassword());

            AuthenticationResponseDTO authenticationResponseDTO = new AuthenticationResponseDTO("Bearer", "Bearer " + token);

            ResponseDTO<AuthenticationResponseDTO> response = new ResponseDTO<AuthenticationResponseDTO>(
                    "auth/successfully-authenticated", "Usuário autenticado com sucesso", authenticationResponseDTO);

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (CustomException customException) {
            ResponseDTO<AuthenticationResponseDTO> response = new ResponseDTO<AuthenticationResponseDTO>(
                    "auth/unauthorized", "Email e/ou senha inválido(s)", null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseDTO<AuthenticationResponseDTO> response = new ResponseDTO<AuthenticationResponseDTO>(
                    "auth/authenticating-error", "Ocorreu um erro ao autenticar o usuário", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
