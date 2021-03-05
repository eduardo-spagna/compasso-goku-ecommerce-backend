package com.compassouol.gokuecommerce.security;

import java.util.ArrayList;
import java.util.List;

import com.compassouol.gokuecommerce.models.Role;
import com.compassouol.gokuecommerce.models.User;
import com.compassouol.gokuecommerce.models.UserRole;
import com.compassouol.gokuecommerce.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ImplementUserDetails implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        final User user = userRepository.findUserByEmail(userEmail);

        if (user == null) {
            throw new UsernameNotFoundException("User '" + userEmail + "' not found");
        }

        List<Role> roles = new ArrayList<>();
        for (UserRole userRole : user.getRoles()) {
            roles.add(userRole.getRole());
        }

        return org.springframework.security.core.userdetails.User.withUsername(userEmail)
                .password(user.getUserPassword()).authorities(roles).accountExpired(false).accountLocked(false)
                .credentialsExpired(false).disabled(false).build();
    }
}
