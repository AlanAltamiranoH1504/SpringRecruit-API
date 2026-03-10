package com.example.springboot_4_initial.dto.vancacy;

import com.example.springboot_4_initial.models.Vacancy;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

public class VacancySpecifications {

    public static Specification<Vacancy> buildQuery(VacancyFilterDTO vacancyFilterDTO) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (vacancyFilterDTO.getContractTypes() != null && !vacancyFilterDTO.getContractTypes().isEmpty()) {
                predicates.add(root.get("contract_type").get("id_contract_type").in(vacancyFilterDTO.getContractTypes()));
            }

            if (vacancyFilterDTO.getIndustriesSector() != null && !vacancyFilterDTO.getIndustriesSector().isEmpty()) {
                predicates.add(root.get("industrialSector").get("id_industrial_sector").in(vacancyFilterDTO.getIndustriesSector()));
            }

            if (vacancyFilterDTO.getWorksModalities() != null && !vacancyFilterDTO.getWorksModalities().isEmpty()) {
                predicates.add(root.get("workModality").get("id_work_modality").in(vacancyFilterDTO.getWorksModalities()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
