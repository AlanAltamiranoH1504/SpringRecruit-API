package com.example.springboot_4_initial.repositories;

import com.example.springboot_4_initial.dto.application.ApplicationByIdRecruiter;
import com.example.springboot_4_initial.dto.application.ApplicationDetailsDTO;
import com.example.springboot_4_initial.models.Application;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.security.PublicKey;
import java.util.List;
import java.util.Optional;

public interface IApplicationRepository extends JpaRepository<Application, Long> {
    @Query("SELECT a FROM Application a WHERE a.candidate.id_candidate = :idCandidate AND a.vacancy.id_vacancy = :idVacancy")
    Optional<Application> isExistsApplication(@Param("idCandidate") Long idCandidate, @Param("idVacancy") Long idVacancy);

    @Query("SELECT a FROM Application a WHERE a.candidate.id_candidate = :idCandidate")
    List<Application> applicationByIdCandidate(@Param("idCandidate") Long idCandidate);

    @Query("""
                SELECT new com.example.springboot_4_initial.dto.application.ApplicationByIdRecruiter(
                    a.id_application, 
                    a.application_date, 
                    a.comments_candidate, 
                    a.status, 
                    v.id_vacancy, 
                    v.name,
                    c.id_candidate,
                    c.name_candidate,
                    c.lastname_candidate
                )
                FROM Application a
                JOIN a.vacancy v
                JOIN a.candidate c
                WHERE v.recruiter.id_recruiter = :idRecruiter
            """)
    List<ApplicationByIdRecruiter> applicationByIdRecruiter(@Param("idRecruiter") Long idRecruiter, Pageable pageable);

    @Query("""
        SELECT new com.example.springboot_4_initial.dto.application.ApplicationDetailsDTO(
            a.id_application,
            a.application_date,
            a.comments_candidate,
            a.status,
            a.url_cv,
            c.id_candidate,
            c.name_candidate,
            c.lastname_candidate,
            c.user.email,
            c.cellphone,
            c.img_profile,
            v.id_vacancy,
            v.name
        )
        FROM Application a
        JOIN a.vacancy v
        JOin a.candidate c
        WHERE v.id_vacancy = :idVacancy
        AND v.recruiter.id_recruiter = :idRecruiter
""")
    List<ApplicationDetailsDTO> getApplicationsDetailsByIdVacancy(@Param("idVacancy") Long idVacancy, @Param("idRecruiter") Long idRecruiter);
}
