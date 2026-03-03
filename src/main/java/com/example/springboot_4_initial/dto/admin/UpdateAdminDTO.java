package com.example.springboot_4_initial.dto.admin;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class UpdateAdminDTO {
    // * tbl_user
    @NotEmpty(message = "El email es requerido")
    @Length(max = 100, message = "El email no puede tener mas de 100 caracteres")
    @Email(message = "Formato de email no valido")
    private String email;

    // * tbl_admin
    @NotEmpty(message = "EL nombre del administrador es requerido")
    @Length(max = 100, message = "EL nombre no puede tener mas de 100 caracteres")
    private String name_admin;

    @NotEmpty(message = "Los apellidos del administrador es requerido")
    @Length(max = 200, message = "Los apellidos no puedem tener mas de 200 caracteres")
    private String lastname_admin;

    @NotEmpty(message = "El telefono es requerido")
    @Length(max = 20, message = "El numero de telefono debe tener maximo 20 numeros")
    private String cellphone;

    @Length(max = 300, message = "La direccion debe tener maximo 300 caracteres")
    private String address;

    @NotNull(message = "El estado del administrador es requerido")
    private Boolean status;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
