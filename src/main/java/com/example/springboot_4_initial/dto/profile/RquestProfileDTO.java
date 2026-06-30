package com.example.springboot_4_initial.dto.profile;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class RquestProfileDTO {
    @NotEmpty(message = "El nombre del perfil es requerido")
    private String profile;
}
