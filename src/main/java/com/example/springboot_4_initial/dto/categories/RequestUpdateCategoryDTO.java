package com.example.springboot_4_initial.dto.categories;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestUpdateCategoryDTO {
    @NotEmpty(message = "El nombre es requerido")
    private String name;
    @NotEmpty(message = "La descripción es requerida")
    private String description;
    @NotNull(message = "El estado es requerido")
    private Boolean status;
}
