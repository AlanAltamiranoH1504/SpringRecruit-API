package com.example.springboot_4_initial.repositories;

import com.example.springboot_4_initial.dto.vancacy.VacancyWithApplicationDTO;
import com.example.springboot_4_initial.models.Vacancy;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IVacancyRepository extends JpaRepository<Vacancy, Long>, JpaSpecificationExecutor<Vacancy> {

    @Query("SELECT v FROM Vacancy v WHERE v.status = :status")
    public abstract List<Vacancy> list_vacancies(@Param("status") boolean status);

    @Query("SELECT v FROM Vacancy v WHERE v.category.id = :id_category")
    public abstract List<Vacancy> list_vacancies_by_category(@Param("id_category") Long id_category);

    @Query("SELECT v FROM Vacancy v WHERE v.name LIKE %:name%")
    public abstract List<Vacancy> list_vacancies_by_name(@Param("name") String name);

    @Query(""" 
            SELECT new com.example.springboot_4_initial.dto.vancacy.VacancyWithApplicationDTO(v, COUNT(a) )
            FROM Vacancy v
            LEFT JOIN Application a ON a.vacancy.id_vacancy = v.id_vacancy
            WHERE v.recruiter.id_recruiter = :idRecruiter
            AND v.progressStatus.id_progress_status IN :idsProgressStatus
            GROUP BY v""")
    public abstract List<VacancyWithApplicationDTO> listVacanciesByIdRecruiter(@Param("idRecruiter") Long idRecruiter, @Param("idsProgressStatus") List<Long> idsProgressStatus, Pageable pageable);
}
