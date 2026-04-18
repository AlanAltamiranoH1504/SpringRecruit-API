package com.example.springboot_4_initial.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.util.List;

public class IsJwtValidDTO {
    @NotBlank(message = "El token jwt es requerido")
    private String token;

    List<
            @Positive(message = "Id de progreso no valido")
            Long> idsProgressStatus;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Long> getIdsProgressStatus() {
        return idsProgressStatus;
    }

    public void setIdsProgressStatus(List<Long> idsProgressStatus) {
        this.idsProgressStatus = idsProgressStatus;
    }
}
