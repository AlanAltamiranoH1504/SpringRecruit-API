package com.example.springboot_4_initial.dto.workModality;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class RequestWorkModalityDTO {
    @NotEmpty(message = "El tipo de modalidad es requerido")
    private String name_work_modality;
}
