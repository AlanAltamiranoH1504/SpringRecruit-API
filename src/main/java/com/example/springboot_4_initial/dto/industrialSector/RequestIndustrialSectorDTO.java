package com.example.springboot_4_initial.dto.industrialSector;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Builder
public class RequestIndustrialSectorDTO {
    @NotEmpty(message = "El nombre del sector industrial es requerido")
    private String name_industrial_sector;
    @NotEmpty(message = "La descripcion del secto industrial es requerido")
    private String description_industrial_sector;
}
