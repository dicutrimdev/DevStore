package com.dominio.devstore.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.dominio.devstore.mapper.UserMapper;
import org.springframework.hateoas.EntityModel;
import com.dominio.devstore.dto.RequestRegisterUser;
import com.dominio.devstore.services.RegisterService;
import com.dominio.devstore.dto.ResponseRegisteredUser;
import com.dominio.devstore.repositories.UserRepository;
import com.dominio.devstore.controllers.LoginController;
import org.springframework.transaction.annotation.Transactional;
import com.dominio.devstore.exceptions.EmailAlreadyUsedException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public EntityModel<ResponseRegisteredUser> register(RequestRegisterUser request) {
        if (userRepository.findByUsername(request.email()).isPresent())
            throw new EmailAlreadyUsedException("Email already registered: " + request.email());

        var user = UserMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var savedUser = userRepository.save(user);

        var response = new ResponseRegisteredUser(
                savedUser.getId(), savedUser.getUsername(), savedUser.getRole()
        );
        return EntityModel.of(response).add(linkTo(methodOn(LoginController.class)
                .login(null)).withRel("login"));
    }
}
