package com.compassouol.gokuecommerce.repositories;

import com.compassouol.gokuecommerce.models.UserRole;
import com.compassouol.gokuecommerce.models.keys.UserRoleKey;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleKey> {
    
}