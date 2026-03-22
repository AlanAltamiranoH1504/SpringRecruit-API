package com.example.springboot_4_initial.dto.contractType;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class CreateContractTypeDTO {
    @NotBlank(message = "EL nombre del tipo de contracto es requerido")
    @Length(max = 100, message = "El nombre no puede tener mas de 100 caracteres")
    private String name_contract_type;

    public String getName_contract_type() {
        return name_contract_type;
    }

    public void setName_contract_type(String name_contract_type) {
        this.name_contract_type = name_contract_type;
    }
}
