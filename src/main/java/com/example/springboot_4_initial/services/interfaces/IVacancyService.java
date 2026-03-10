package com.example.springboot_4_initial.services.interfaces;

import com.example.springboot_4_initial.dto.vancacy.CreateVacancyDTO;
import com.example.springboot_4_initial.dto.vancacy.SaveVacanciesDTO;
import com.example.springboot_4_initial.dto.vancacy.VacancyFilterDTO;
import com.example.springboot_4_initial.models.Vacancy;

import java.util.List;

public interface IVacancyService {
    public abstract List<Vacancy> list_vacancies(boolean status);
    public abstract Vacancy save_vacancy(CreateVacancyDTO createVacancyDTO);
    public abstract Vacancy get_vacancy(Long id);
    public abstract boolean delete_vacancy(Long id);
    public abstract Vacancy update_img_vacancy(String path_img, Long idVacancy);
    public abstract List<Vacancy> list_vacancies_by_category(Long id_category);
    public abstract List<Vacancy> list_vacancies_by_name(String name);
    public abstract List<Vacancy> search_vacancies(VacancyFilterDTO vacancyFilterDTO);
    public abstract boolean save_vacancies(SaveVacanciesDTO saveVacanciesDTO);
}
