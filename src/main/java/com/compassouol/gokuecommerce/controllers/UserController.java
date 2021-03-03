package com.compassouol.gokuecommerce.controllers;

import javax.validation.Valid;

import com.compassouol.gokuecommerce.dtos.request.user.CreateUserRequestDTO;
import com.compassouol.gokuecommerce.dtos.response.ErrorResponseDTO;
import com.compassouol.gokuecommerce.dtos.response.ResponseDTO;
import com.compassouol.gokuecommerce.dtos.response.user.ShowUserResponseDTO;
import com.compassouol.gokuecommerce.models.User;
import com.compassouol.gokuecommerce.services.UserService;

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
@RequestMapping(value = "/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    @ApiOperation(value = "Create a user")
    public ResponseEntity<ResponseDTO<?>> create(@Valid @RequestBody CreateUserRequestDTO createUser,
            BindingResult result) {
        try {
            if (result.hasErrors() == true) {
                ErrorResponseDTO<Object> errorResponseDTO = new ErrorResponseDTO<Object>(
                        result.getFieldError().getCode(), result.getFieldError().getField(),
                        result.getFieldError().getDefaultMessage(), result.getFieldError().getRejectedValue());
                ResponseDTO<ErrorResponseDTO<Object>> response = new ResponseDTO<ErrorResponseDTO<Object>>(
                        "user/invalid-data", "Dados inválidos", errorResponseDTO);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            User newUser = userService.create(createUser);

            ShowUserResponseDTO showUserResponseDTO = new ShowUserResponseDTO(newUser.getUserId(),
                    newUser.getUserName(), newUser.getUserEmail());

            ResponseDTO<ShowUserResponseDTO> response = new ResponseDTO<ShowUserResponseDTO>(
                    "user/successfully-created", "Usuário criado com sucesso", showUserResponseDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseDTO<ShowUserResponseDTO> response = new ResponseDTO<ShowUserResponseDTO>("user/creating-error",
                    "Ocorreu um erro ao criar o usuário", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
