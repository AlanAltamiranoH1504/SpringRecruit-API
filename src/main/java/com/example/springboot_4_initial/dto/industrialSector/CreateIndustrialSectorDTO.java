package com.example.springboot_4_initial.dto.industrialSector;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class CreateIndustrialSectorDTO {
    @NotBlank(message = "El nombre del sector es requerido")
    @Length(max = 100, message = "El nombre del sector no puede ser mayor a 100 caracteres")
    private String name_industrial_sector;

    @NotBlank(message = "La descripción del sector es requerida")
    @Length(max = 150, message = "La descripción no puede ser mayor a 150 caracteres")
    private String description_industrial_sector;

    public String getName_industrial_sector() {
        return name_industrial_sector;
    }

    public void setName_industrial_sector(String name_industrial_sector) {
        this.name_industrial_sector = name_industrial_sector;
    }

    public String getDescription_industrial_sector() {
        return description_industrial_sector;
    }

    public void setDescription_industrial_sector(String description_industrial_sector) {
        this.description_industrial_sector = description_industrial_sector;
    }
}
