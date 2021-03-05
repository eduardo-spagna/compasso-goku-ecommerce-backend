package com.compassouol.gokuecommerce.services;

import com.compassouol.gokuecommerce.exceptions.CustomException;
import com.compassouol.gokuecommerce.repositories.UserRepository;
import com.compassouol.gokuecommerce.security.JwtTokenProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String signin(String username, String password) throws CustomException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username, userRepository.findUserByEmail(username).getRoles());
        } catch (AuthenticationException e) {
            throw new CustomException("Email e/ou senha invalido(s)", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
