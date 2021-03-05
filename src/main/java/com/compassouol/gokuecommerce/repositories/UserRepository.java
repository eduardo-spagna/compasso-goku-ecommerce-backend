package com.compassouol.gokuecommerce.repositories;

import com.compassouol.gokuecommerce.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users WHERE user_email = :userEmail", nativeQuery = true)
    User findUserByEmail(String userEmail);
}
