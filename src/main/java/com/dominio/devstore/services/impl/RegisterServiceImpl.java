package com.dominio.devstore.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.dominio.devstore.mapper.UserMapper;
import com.dominio.devstore.dto.RequestRegisterUser;
import com.dominio.devstore.services.RegisterService;
import com.dominio.devstore.dto.ResponseRegisteredUser;
import com.dominio.devstore.repositories.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import com.dominio.devstore.exceptions.EmailAlreadyUsedException;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public ResponseRegisteredUser register(RequestRegisterUser request) {
        if (userRepository.findByUsername(request.email()).isPresent())
            throw new EmailAlreadyUsedException("Email already registered: " + request.email());

        var user = UserMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        var savedUser = userRepository.save(user);
        return new ResponseRegisteredUser(savedUser.getId(), savedUser.getUsername(), savedUser.getRole());
    }
}
