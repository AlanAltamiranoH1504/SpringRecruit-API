package com.example.springboot_4_initial.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_contract_types")
@JsonPropertyOrder({"id_contract_type", "name_contract_type", "status"})
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Getter @Setter
@Builder
public class ContractType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_contract_type;
    private String name_contract_type;
    private boolean status;
    @OneToMany(mappedBy = "contract_type")
    @JsonIgnore
    private List<Vacancy> vacancies = new ArrayList<>();
}
