package com.example.springboot_4_initial.dto.auth;

import jakarta.validation.constraints.NotBlank;

public class IsJwtValidDTO {
    @NotBlank(message = "El token jwt es requerido")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
