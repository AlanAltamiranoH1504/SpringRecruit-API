package com.example.springboot_4_initial.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_industrial_sectors")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter @Setter
@Builder
public class IndustrialSector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_industrial_sector;
    private String name_industrial_sector;
    private String description_industrial_sector;
    private boolean status;
    @OneToMany(mappedBy = "industrialSector")
    @JsonIgnore
    private List<Vacancy> vacancies = new ArrayList<>();
}
