package com.compassouol.gokuecommerce.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public abstract class GenerateHashPasswordUtil {
    public static final String SALT = "#saltgoku";

    public static String generateHash(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password + SALT);
    }

    public static boolean isPasswordValid(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword + SALT, encodedPassword);
    }
}
