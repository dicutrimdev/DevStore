package com.dominio.devstore.services;

import com.dominio.devstore.entities.UserCredentials;

public interface TokenService {

    String generateToken(UserCredentials userCredentials);

    String validateToken(String token);
}
