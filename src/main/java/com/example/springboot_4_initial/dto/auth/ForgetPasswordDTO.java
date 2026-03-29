package com.example.springboot_4_initial.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ForgetPasswordDTO {
    @NotBlank(message = "El email para la recuperación de cuenta es obligatorio")
    @Email(message = "El formato del email no es valido")
    String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
