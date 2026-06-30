package com.example.springboot_4_initial.dto.progressStatus;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class RequestProgressStatusDTO {
    @NotEmpty(message = "El nombre es requerido")
    private String name_progress_status;
    @NotEmpty(message = "La descripcion es requerida")
    private String description_progress_status;
}
