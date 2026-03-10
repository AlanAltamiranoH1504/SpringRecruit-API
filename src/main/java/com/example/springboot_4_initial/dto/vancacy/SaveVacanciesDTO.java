package com.example.springboot_4_initial.dto.vancacy;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class SaveVacanciesDTO {

    @NotEmpty(message = "El array de vacantes es requerio")
    List<CreateVacancyDTO> vacancies;

    public List<CreateVacancyDTO> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<CreateVacancyDTO> vacancies) {
        this.vacancies = vacancies;
    }
}
