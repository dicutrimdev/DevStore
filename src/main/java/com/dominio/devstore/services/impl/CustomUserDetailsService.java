package com.dominio.devstore.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.dominio.devstore.entities.UserCredentials;
import com.dominio.devstore.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                        "User not found with email: " + username
                )
        );
    }
}
