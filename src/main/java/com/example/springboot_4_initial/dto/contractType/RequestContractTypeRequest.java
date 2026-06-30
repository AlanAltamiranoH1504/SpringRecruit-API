package com.example.springboot_4_initial.dto.contractType;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class RequestContractTypeRequest {
    @NotEmpty(message = "El nombre del tipo de contrato es requerido")
    private String nameContractType;
}
