package com.example.springboot_4_initial.dto.vancacy;

import com.example.springboot_4_initial.models.Vacancy;

public class VacancyWithApplicationDTO {
    private Vacancy vacancy;
    private Long totalApplications;

    public VacancyWithApplicationDTO() {
    }

    public VacancyWithApplicationDTO(Vacancy vacancy, Long totalApplications) {
        this.vacancy = vacancy;
        this.totalApplications = totalApplications;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    public Long getTotalApplications() {
        return totalApplications;
    }

    public void setTotalApplications(Long totalApplications) {
        this.totalApplications = totalApplications;
    }
}
