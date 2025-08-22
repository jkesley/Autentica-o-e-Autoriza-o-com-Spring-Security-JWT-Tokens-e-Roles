package com.seguranca.aula_seguranca.busines_service;

import com.seguranca.aula_seguranca.infrastructure.entitys.User;
import com.seguranca.aula_seguranca.infrastructure.role.UserRole;
import com.seguranca.aula_seguranca.infrastructure.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private final UserRepository repository;

    public AuthorizationService(UserRepository repository) {
        this.repository = repository;
    }

    public boolean userExists(String login) {
        return repository.findByUsername(login) != null; // 👈 usar o mesmo método que declarou
    }

    public void registerUser(String login, String password, String roleStr) {
        if (userExists(login)) {
            throw new RuntimeException("Usuário já existe!");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(password);

        UserRole roleEnum;
        try {
            roleEnum = UserRole.valueOf(roleStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Role inválido! Use ADMIN ou USER.");
        }

        User newUser = new User(login, encryptedPassword, roleEnum);
        repository.save(newUser);
    }
    public User findByLogin(String login) {
        return repository.findByUsername(login); // 👈 aqui também
    }
}

