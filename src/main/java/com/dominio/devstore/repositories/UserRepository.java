package com.dominio.devstore.repositories;

import com.dominio.devstore.entities.User;
import com.dominio.devstore.entities.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserCredentials, Integer> {
    Optional<UserCredentials> findByUsername(String email);
}
