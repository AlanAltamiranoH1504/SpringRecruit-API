package com.example.springboot_4_initial.dto.industrialSector;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class RequestUpdateIndustrialSectorDTO {
    @NotEmpty(message = "El nombre del sector industrial es requerido")
    private String name_industrial_sector;
    @NotEmpty(message = "La descripcion del secto industrial es requerido")
    private String description_industrial_sector;
    @NotNull(message = "El estado es requerido")
    private Boolean status;
}
