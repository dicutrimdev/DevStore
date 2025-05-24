package com.dominio.devstore.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.dominio.devstore.mapper.UserMapper;
import com.dominio.devstore.dto.RequestRegisterUser;
import com.dominio.devstore.dto.ResponseRegisteredUser;
import com.dominio.devstore.repositories.UserRepository;
import com.dominio.devstore.services.AuthenticationService;
import org.springframework.transaction.annotation.Transactional;
import com.dominio.devstore.exceptions.EmailAlreadyUsedException;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public ResponseRegisteredUser register(RequestRegisterUser request) {
        if (userRepository.findByEmail(request.email()).isPresent())
            throw new EmailAlreadyUsedException("Email already registered: " + request.email());

        var user = UserMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        var savedUser = userRepository.save(user);
        return new ResponseRegisteredUser(savedUser.getId(), savedUser.getName());
    }
}
