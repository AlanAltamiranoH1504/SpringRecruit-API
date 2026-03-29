package com.example.springboot_4_initial.dto.application;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

public class UpdateApplicationDTO {
    @NotNull(message = "El id de la postulación es requerido")
    @Positive(message = "El id de la postulacion no es valido")
    private Long idApplication;

    @NotBlank(message = "El por actualizar es requerido")
    private String status;

    @Length(max = 5000, message = "Las notas no pueden ser mayores a 5000 caracteres")
    private String notes_recruiter;

    public Long getIdApplication() {
        return idApplication;
    }

    public void setIdApplication(Long idApplication) {
        this.idApplication = idApplication;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes_recruiter() {
        return notes_recruiter;
    }

    public void setNotes_recruiter(String notes_recruiter) {
        this.notes_recruiter = notes_recruiter;
    }
}
