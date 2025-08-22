package com.seguranca.aula_seguranca.controller;

import com.seguranca.aula_seguranca.DTO.RegisterDTO;
import com.seguranca.aula_seguranca.infrastructure.entitys.User;
import com.seguranca.aula_seguranca.infrastructure.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository repository;

    // ✅ Injeção via construtor
    public AutenticationController(AuthenticationManager authenticationManager, UserRepository repository) {
        this.authenticationManager = authenticationManager;
        this.repository = repository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data) {
        if (this.repository.findByUsername(data.login()) != null) {
            return ResponseEntity.badRequest().body("Usuário já existe!");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());

        this.repository.save(newUser);
        return ResponseEntity.ok("Usuário registrado com sucesso!");
    }
}
