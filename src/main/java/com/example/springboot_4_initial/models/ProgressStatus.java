package com.example.springboot_4_initial.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

import java.util.List;

@Entity()
@Table(name = "tbl_progress_status")
@JsonPropertyOrder({"id_progress_status", "name_progress_status", "description_progress_status", "status"})
public class ProgressStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_progress_status;
    private String name_progress_status;
    private String description_progress_status;
    private boolean status;
    @OneToMany(mappedBy = "progressStatus")
    @JsonIgnore
    private List<Vacancy> vacancies;

    public ProgressStatus() {
    }

    public ProgressStatus(String name_progress_status, String description_progress_status, boolean status) {
        this.name_progress_status = name_progress_status;
        this.description_progress_status = description_progress_status;
        this.status = status;
    }

    public Long getId_progress_status() {
        return id_progress_status;
    }

    public void setId_progress_status(Long id_progress_status) {
        this.id_progress_status = id_progress_status;
    }

    public String getName_progress_status() {
        return name_progress_status;
    }

    public void setName_progress_status(String name_progress_status) {
        this.name_progress_status = name_progress_status;
    }

    public String getDescription_progress_status() {
        return description_progress_status;
    }

    public void setDescription_progress_status(String description_progress_status) {
        this.description_progress_status = description_progress_status;
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
