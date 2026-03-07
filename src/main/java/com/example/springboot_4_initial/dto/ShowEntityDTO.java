package com.example.springboot_4_initial.dto;

import jakarta.validation.constraints.NotEmpty;

public class ShowEntityDTO {
    @NotEmpty(message = "El id encriptado de la entidad es necesario")
    private String id_entity_crypt;

    public String getId_entity_crypt() {
        return id_entity_crypt;
    }

    public void setId_entity_crypt(String id_entity_crypt) {
        this.id_entity_crypt = id_entity_crypt;
    }
}
