package com.example.springboot_4_initial.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_progress_status")
@JsonPropertyOrder({"id_progress_status", "name_progress_status", "description_progress_status", "status"})
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter @Setter
@Builder
public class ProgressStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_progress_status;
    private String name_progress_status;
    private String description_progress_status;
    private boolean status;
    @OneToMany(mappedBy = "progressStatus")
    @JsonIgnore
    private List<Vacancy> vacancies =  new ArrayList<>();
}
