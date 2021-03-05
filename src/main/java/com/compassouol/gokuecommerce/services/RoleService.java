package com.compassouol.gokuecommerce.services;

import java.util.Optional;

import com.compassouol.gokuecommerce.models.Role;
import com.compassouol.gokuecommerce.repositories.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role findRoleById(long roleId) {
        Optional<Role> role = roleRepository.findById(roleId);

        if (role.isPresent() == true) {
            return role.get();
        }

        return null;
    }
}
