package com.example.springboot_4_initial.dto.auth;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class SaveNewPasswordDTO {
    @NotBlank(message = "El token de reset de password es requerido")
    @Length(max = 150, message = "Token de reset no valido")
    private String token;

    @NotBlank(message = "Los digitos de reset de password son requeridos")
    @Length(max = 5, message = "Digitos de reset no validos")
    private String randomNumber;

    @NotBlank(message = "El password nuevo es requerido")
    @Length(min = 8, message = "El password nuevo debe tener al menos 8 caracteres")
    private String newPassword;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRandomNumber() {
        return randomNumber;
    }

    public void setRandomNumber(String randomNumber) {
        this.randomNumber = randomNumber;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
