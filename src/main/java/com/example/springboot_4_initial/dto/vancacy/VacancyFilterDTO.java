package com.example.springboot_4_initial.dto.vancacy;

import java.util.List;

public class VacancyFilterDTO {
    List<Long> contractTypes;
    List<Long> industriesSector;
    List<Long> worksModalities;
    List<Long> categories;

    public List<Long> getContractTypes() {
        return contractTypes;
    }

    public void setContractTypes(List<Long> contractTypes) {
        this.contractTypes = contractTypes;
    }

    public List<Long> getIndustriesSector() {
        return industriesSector;
    }

    public void setIndustriesSector(List<Long> industriesSector) {
        this.industriesSector = industriesSector;
    }

    public List<Long> getWorksModalities() {
        return worksModalities;
    }

    public void setWorksModalities(List<Long> worksModalities) {
        this.worksModalities = worksModalities;
    }

    public List<Long> getCategories() {
        return categories;
    }

    public void setCategories(List<Long> categories) {
        this.categories = categories;
    }
}
