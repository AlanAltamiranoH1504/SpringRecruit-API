package com.example.springboot_4_initial.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestFindEntityDTO   {
    @NotNull(message = "El id de busqueda es requerido")
    private Long idEntity;
}
