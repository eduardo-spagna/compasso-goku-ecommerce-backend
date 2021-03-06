package com.compassouol.gokuecommerce.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.compassouol.gokuecommerce.dtos.request.user.CreateUserRequestDTO;
import com.compassouol.gokuecommerce.enums.RoleEnum;
import com.compassouol.gokuecommerce.models.Role;
import com.compassouol.gokuecommerce.models.User;
import com.compassouol.gokuecommerce.repositories.UserRepository;
import com.compassouol.gokuecommerce.utils.GenerateHashPasswordUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    public User findUserById(long userId) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent() == true) {
            return user.get();
        }

        return null;
    }

    public User findUserByEmail(String userEmail) {
        return userRepository.findUserByEmail(userEmail);
    }

    @Transactional
    public User create(CreateUserRequestDTO createUser) {
        User user = new User();

        user.setUserName(createUser.getUserName());
        user.setUserEmail(createUser.getUserEmail());
        user.setUserPassword(GenerateHashPasswordUtil.generateHash(createUser.getUserPassword()));

        User createdUser = userRepository.save(user);

        List<Role> roles = new ArrayList<>();

        if (createUser.isAdmin() == true) {
            roles.add(roleService.findRoleById(RoleEnum.ROLE_ADMIN.getRoleId()));
        } else {
            roles.add(roleService.findRoleById(RoleEnum.ROLE_COMMON_USER.getRoleId()));
        }

        userRoleService.createUserRole(createdUser, roles);

        return createdUser;
    }
}
