package com.compassouol.gokuecommerce.services;

import com.compassouol.gokuecommerce.dtos.request.user.CreateUserRequestDTO;
import com.compassouol.gokuecommerce.models.User;
import com.compassouol.gokuecommerce.repositories.UserRepository;
import com.compassouol.gokuecommerce.utils.GenerateHashPasswordUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User create(CreateUserRequestDTO createUser) {
        User user = new User();

        user.setUserName(createUser.getUserName());
        user.setUserEmail(createUser.getUserEmail());
        user.setUserPassword(GenerateHashPasswordUtil.generateHash(createUser.getUserPassword()));

        return userRepository.save(user);
    }
}
