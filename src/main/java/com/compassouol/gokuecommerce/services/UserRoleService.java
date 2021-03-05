package com.compassouol.gokuecommerce.services;

import java.util.ArrayList;
import java.util.List;

import com.compassouol.gokuecommerce.models.Role;
import com.compassouol.gokuecommerce.models.User;
import com.compassouol.gokuecommerce.models.UserRole;
import com.compassouol.gokuecommerce.models.keys.UserRoleKey;
import com.compassouol.gokuecommerce.repositories.UserRoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;

    public List<UserRole> createUserRole(User user, List<Role> roles) {
        List<UserRole> userRoles = new ArrayList<>();

        for (Role role : roles) {
            UserRole userRole = new UserRole();
            UserRoleKey userRoleKey = new UserRoleKey(user.getUserId(), role.getRoleId());

            userRole.setUser(user);
            userRole.setRole(role);
            userRole.setUserRoleKey(userRoleKey);
            userRoles.add(userRole);
        }

        return userRoleRepository.saveAll(userRoles);
    }
}
