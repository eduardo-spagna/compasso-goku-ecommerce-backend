package com.compassouol.gokuecommerce.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public abstract class GenerateHashPasswordUtil {
    public static String generateHash(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    public static boolean isPasswordValid(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
