package com.dominio.devstore.services;

import com.dominio.devstore.dto.RequestRegisterUser;
import com.dominio.devstore.dto.ResponseRegisteredUser;

public interface AuthenticationService {
    ResponseRegisteredUser register(RequestRegisterUser request);
}
