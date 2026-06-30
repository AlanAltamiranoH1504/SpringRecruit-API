package com.example.springboot_4_initial.dto.profile;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class RequestUpdateProfileDTO {
    @NotEmpty(message = "El nombre del perfil es requerido")
    private String profile;
    @NotNull(message = "El estado es requerido")
    private Boolean status;
}
