package com.example.springboot_4_initial.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_work_modalities")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter @Setter
@Builder
public class WorkModality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_work_modality;
    private String name_work_modality;
    private boolean status;
    @OneToMany(mappedBy = "workModality")
    @JsonIgnore
    private List<Vacancy> vacancies = new ArrayList<>();
}
