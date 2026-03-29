package com.example.springboot_4_initial.dto.auth;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class UpdatePasswordDTO {
    @NotBlank(message = "El token de reset de password es requerido")
    @Length(max = 150, message = "El token no es valido")
    String tokeResetPassword;

    @NotBlank(message = "Los digitos para actualización son requeridos")
    @Length(max = 5, message = "Los digitos no son validos")
    public String randomeNumber;

    public String getTokeResetPassword() {
        return tokeResetPassword;
    }

    public void setTokeResetPassword(String tokeResetPassword) {
        this.tokeResetPassword = tokeResetPassword;
    }

    public String getRandomeNumber() {
        return randomeNumber;
    }

    public void setRandomeNumber(String randomeNumber) {
        this.randomeNumber = randomeNumber;
    }
}
