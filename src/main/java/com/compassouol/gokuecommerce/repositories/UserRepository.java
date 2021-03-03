package com.compassouol.gokuecommerce.repositories;

import com.compassouol.gokuecommerce.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
