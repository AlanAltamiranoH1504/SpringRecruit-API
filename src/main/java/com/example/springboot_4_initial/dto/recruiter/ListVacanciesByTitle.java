package com.example.springboot_4_initial.dto.recruiter;

import com.example.springboot_4_initial.dto.auth.IsJwtValidDTO;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class ListVacanciesByTitle extends IsJwtValidDTO {
    @NotBlank(message = "El titulo es obligatorio")
    @Length(max = 100, min = 2, message = "El titulo no es valido")
    private String titleVacancy;

    public ListVacanciesByTitle(String titleVacancy) {
        super();
        this.titleVacancy = titleVacancy;
    }

    public String getTitleVacancy() {
        return titleVacancy;
    }

    public void setTitleVacancy(String titleVacancy) {
        this.titleVacancy = titleVacancy;
    }
}
