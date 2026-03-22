package com.example.springboot_4_initial.dto.contractType;

import jakarta.validation.constraints.NotNull;

public class UpdateContractTypeDTO extends CreateContractTypeDTO{
    @NotNull(message = "El status es requerido (true/false)")
    private Boolean status;

    public UpdateContractTypeDTO() {
        super();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
