package com.example.springboot_4_initial.dto.workModality;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Builder
public class RequestUpdateWorkModalityDTO {
    @NotEmpty(message = "El tipo de modalidad es requerido")
    private String name_work_modality;
    @NotNull(message = "El estado es requerido")
    private Boolean status;
}
