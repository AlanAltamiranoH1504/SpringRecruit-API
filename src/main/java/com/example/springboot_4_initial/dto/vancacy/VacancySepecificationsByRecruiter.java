package com.example.springboot_4_initial.dto.vancacy;

import com.example.springboot_4_initial.models.Vacancy;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.criteria.Predicate;

public class VacancySepecificationsByRecruiter {
    public static Specification<Vacancy> buildQuery(SearchByFilterAndRecruiter searchByFilterAndRecruiter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (searchByFilterAndRecruiter.getContractTypes() != null && !searchByFilterAndRecruiter.getContractTypes().isEmpty()) {
                predicates.add(root.get("contract_type").get("id_contract_type").in(searchByFilterAndRecruiter.getContractTypes()));
            }

            if (searchByFilterAndRecruiter.getIndustriesSector() != null && !searchByFilterAndRecruiter.getIndustriesSector().isEmpty()) {
                predicates.add(root.get("industrialSector").get("id_industrial_sector").in(searchByFilterAndRecruiter.getIndustriesSector()));
            }

            if (searchByFilterAndRecruiter.getWorksModalities() != null && !searchByFilterAndRecruiter.getWorksModalities().isEmpty()) {
                predicates.add(root.get("workModality").get("id_work_modality").in(searchByFilterAndRecruiter.getWorksModalities()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
