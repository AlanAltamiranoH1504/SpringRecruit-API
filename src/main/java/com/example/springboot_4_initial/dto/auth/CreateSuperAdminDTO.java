package com.example.springboot_4_initial.dto.auth;

import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import org.springframework.mail.MailException;

public class CreateSuperAdminDTO {
    // * Data tbl_user
    @NotEmpty(message = "El email es requerido")
    @Length(max = 100, message = "El email no debe ser mayor a 100 caracteres")
    private String email;

    @NotEmpty(message = "El password es requerida")
    @Length(min = 8, max = 100, message = "El password debe tener al menos 8 caracteres")
    private String password;

    // * Data tbl_admin
    @NotEmpty(message = "El nombre es requerido")
    @Length(max = 100, message = "EL nombre debe tener maximo 100 caracteres")
    private String name_admin;

    @NotEmpty(message = "Los apellidos son requeridos")
    @Length(max = 200, message = "Los apellidos deben tener maximo 200 caracteres")
    private String lastname_admin;

    @NotEmpty(message = "El telefono es requerido")
    @Length(max = 20, message = "El numero de telefono debe tener maximo 20 numeros")
    private String cellphone;

    @Length(max = 300, message = "La direccion debe tener maximo 300 caracteres")
    private String address;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName_admin() {
        return name_admin;
    }

    public void setName_admin(String name_admin) {
        this.name_admin = name_admin;
    }

    public String getLastname_admin() {
        return lastname_admin;
    }

    public void setLastname_admin(String lastname_admin) {
        this.lastname_admin = lastname_admin;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
