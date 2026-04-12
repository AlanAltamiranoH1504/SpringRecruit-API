package com.example.springboot_4_initial.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity()
@Table(name = "tbl_industrial_sectors")
public class IndustrialSector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_industrial_sector;
    private String name_industrial_sector;
    private String description_industrial_sector;
    private boolean status;
    @OneToMany(mappedBy = "industrialSector")
    @JsonIgnore
    private List<Vacancy> vacancies;

    public IndustrialSector() {
    }

    public IndustrialSector(String name_industrial_sector, String description_industrial_sector, boolean status) {
        this.name_industrial_sector = name_industrial_sector;
        this.description_industrial_sector = description_industrial_sector;
        this.status = status;
    }

    public Long getId_industrial_sector() {
        return id_industrial_sector;
    }

    public void setId_industrial_sector(Long id_industrial_sector) {
        this.id_industrial_sector = id_industrial_sector;
    }

    public String getName_industrial_sector() {
        return name_industrial_sector;
    }

    public void setName_industrial_sector(String name_industrial_sector) {
        this.name_industrial_sector = name_industrial_sector;
    }

    public String getDescription_industrial_sector() {
        return description_industrial_sector;
    }

    public void setDescription_industrial_sector(String description_industrial_sector) {
        this.description_industrial_sector = description_industrial_sector;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
