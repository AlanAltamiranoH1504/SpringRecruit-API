package com.example.springboot_4_initial.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

import java.util.List;

@Entity()
@Table(name = "tbl_contract_types")
@JsonPropertyOrder({"id_contract_type", "name_contract_type", "status"})
public class ContractType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_contract_type;
    private String name_contract_type;
    private boolean status;
    @OneToMany(mappedBy = "contract_type")
    @JsonIgnore
    private List<Vacancy> vacancies;

    public ContractType() {
    }

    public ContractType(String name_contract_type, boolean status) {
        this.name_contract_type = name_contract_type;
        this.status = status;
    }

    public Long getId_contract_type() {
        return id_contract_type;
    }

    public void setId_contract_type(Long id_contract_type) {
        this.id_contract_type = id_contract_type;
    }

    public String getName_contract_type() {
        return name_contract_type;
    }

    public void setName_contract_type(String name_contract_type) {
        this.name_contract_type = name_contract_type;
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
