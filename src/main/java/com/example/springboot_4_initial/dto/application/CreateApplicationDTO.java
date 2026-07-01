package com.example.springboot_4_initial.dto.application;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;


public class CreateApplicationDTO {
    @Length(max = 5000, message = "Los comentarios no pueden ser mayor a 5000 caracteres")
    private String comments_candidate;

    @NotNull(message = "El id de la vacante es requerido")
    @Min(value = 1, message = "El id de la vacante no es valido")
    private Long idVacancy;

    @NotNull(message = "El id del candidato es requerido")
    @Min(value = 1, message = "El id del candidato no es valido")
    private Long idCandidate;

    public String getComments_candidate() {
        return comments_candidate;
    }

    public void setComments_candidate(String comments_candidate) {
        this.comments_candidate = comments_candidate;
    }

    public Long getIdVacancy() {
        return idVacancy;
    }

    public void setIdVacancy(Long idVacancy) {
        this.idVacancy = idVacancy;
    }

    public Long getIdCandidate() {
        return idCandidate;
    }

    public void setIdCandidate(Long idCandidate) {
        this.idCandidate = idCandidate;
    }
}
