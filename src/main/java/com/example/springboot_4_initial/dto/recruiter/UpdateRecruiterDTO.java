package com.example.springboot_4_initial.dto.recruiter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class UpdateRecruiterDTO {
    // * tbl_users
    @NotEmpty(message = "El email es requerido")
    @Email(message = "Formato de email no valido")
    @Length(max = 100, message = "EL email no puede tener mas de 100 caracteres")
    private String email;

    // * tbl_recruiters
    @NotEmpty(message = "El nombre es requerido")
    @Length(max = 100, message = "El nombre no puede ser mayor a 100 caracteres")
    private String name;

    @NotEmpty(message = "Los apellidos son requeridos")
    @Length(max = 200, message = "Los apellidos no pueden tener una longitud mayor a 200 caracteres")
    private String surnames;

    @NotEmpty(message = "El username es requerido")
    @Length(max = 150, message = "EL username no puede ser mayor a 150 caracteres")
    private String username;

    @NotNull(message = "El estado del perfil es requerido")
    private Boolean status;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
