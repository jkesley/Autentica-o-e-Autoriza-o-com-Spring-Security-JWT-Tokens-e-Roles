package com.seguranca.aula_seguranca.infrastructure.role;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserRole {

    ADMIN("admin"),
    USER("user");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    @JsonValue // quando for serializar, usa este valor
    public String getRole() {
        return role;
    }

    @JsonCreator // quando receber JSON, converte para o enum certo
    public static UserRole fromValue(String value) {
        for (UserRole role : UserRole.values()) {
            if (role.role.equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid role: " + value);
    }
}
