package com.dominio.devstore.services;

import com.dominio.devstore.entities.CustomUserDetails;

public interface TokenService {

    String generateToken(CustomUserDetails customUserDetails);

    String validateToken(String token);
}
