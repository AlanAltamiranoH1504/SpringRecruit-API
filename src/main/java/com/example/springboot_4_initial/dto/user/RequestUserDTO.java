package com.example.springboot_4_initial.dto.user;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Builder
public class RequestUserDTO {
    @NotEmpty(message = "El nombre es requerido")
    @Size(min = 3, max = 50, message = "El nombre no debe ser mayor a 50 caracteres")
    @Pattern(regexp = "^[\\p{L} ]+$", message = "Solo se admiten letras y espacios")
    private String name;

    @NotEmpty(message = "Los apellidos son requeridos")
    @Size(min = 3, max = 200, message = "Los apellidos no deben ser mayor a 200 caracteres")
    @Pattern(regexp = "^[\\p{L} ]+$", message = "Solo se admiten letras y espacios")
    private String surnames;

    @NotEmpty(message = "El email es requerido")
    @Size(max = 100, message = "Email no valido")
    @Email(message = "El formato de email no es valido")
    private String email;

    @NotEmpty(message = "El username es requerido")
    @Size(max = 45, message = "El username no debe superar los 45 caracteres")
    private String username;

    @NotEmpty(message = "El password es requerido")
    @Size(min = 8, max = 100, message = "El password debe ser de al menos 8 caracteres")
    private String password;

    private String img_profile;
}
