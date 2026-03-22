package com.example.springboot_4_initial.dto.industrialSector;

import jakarta.validation.constraints.NotNull;

public class UpdateIndustrialSectorDTO extends CreateIndustrialSectorDTO{
    @NotNull(message = "El estado es requerido (true/false)")
    Boolean status;
    public UpdateIndustrialSectorDTO() {
        super();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
