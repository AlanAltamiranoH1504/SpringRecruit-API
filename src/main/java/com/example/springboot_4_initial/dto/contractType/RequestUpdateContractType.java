package com.example.springboot_4_initial.dto.contractType;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class RequestUpdateContractType {
    @NotEmpty(message = "El nombre del tipo de contracto es requerido")
    private String name_contract_type;
    @NotNull(message = "El status es requerido")
    private Boolean status;
}
