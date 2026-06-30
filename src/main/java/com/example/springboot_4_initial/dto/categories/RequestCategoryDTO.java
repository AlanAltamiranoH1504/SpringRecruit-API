package com.example.springboot_4_initial.dto.categories;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
public class RequestCategoryDTO {
    @NotEmpty(message = "El nombre de la categoria es requerido")
    @NotNull(message = "El nombre de la categoria es requerido")
    private String name;
    @NotEmpty(message = "La descripcion de la categoria es requerida")
    @NotNull(message = "La descripcion de la categoria es requerida")
    private String description;
}
