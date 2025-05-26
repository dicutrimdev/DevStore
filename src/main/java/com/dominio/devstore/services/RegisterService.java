package com.dominio.devstore.services;

import com.dominio.devstore.dto.RequestRegisterUser;
import com.dominio.devstore.dto.ResponseRegisteredUser;
import org.springframework.hateoas.EntityModel;

public interface RegisterService {
    EntityModel<ResponseRegisteredUser> register(RequestRegisterUser request);
}
