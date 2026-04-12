package com.example.springboot_4_initial.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity()
@Table(name = "tbl_work_modalities")
public class WorkModality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_work_modality;
    private String name_work_modality;
    private boolean status;
    @OneToMany(mappedBy = "workModality")
    @JsonIgnore
    private List<Vacancy> vacancies;

    public WorkModality() {
    }

    public WorkModality(String name_work_modality, boolean status) {
        this.name_work_modality = name_work_modality;
        this.status = status;
    }

    public Long getId_work_modality() {
        return id_work_modality;
    }

    public void setId_work_modality(Long id_work_modality) {
        this.id_work_modality = id_work_modality;
    }

    public String getName_work_modality() {
        return name_work_modality;
    }

    public void setName_work_modality(String name_work_modality) {
        this.name_work_modality = name_work_modality;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Vacancy> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }
}
