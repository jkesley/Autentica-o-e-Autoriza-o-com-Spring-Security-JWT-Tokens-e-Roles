package com.seguranca.aula_seguranca.DTO;

import com.seguranca.aula_seguranca.infrastructure.role.UserRole;

public record RegisterDTO(
        String login,
        String password,
        UserRole role
) {}
