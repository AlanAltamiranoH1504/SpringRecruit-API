package com.example.springboot_4_initial.dto.application;

import com.example.springboot_4_initial.models.enums.ApplicationStatus;

import java.time.LocalDateTime;

public class ApplicationByIdRecruiter {
    private Long idApplication;
    private LocalDateTime applicationDate;
    private String commentsCandidate;
    private ApplicationStatus status;
    private Long idVacancy;
    private String nameVacancy;
    private Long idCandidate;
    private String nameCandidate;
    private String surnameCandidate;


    public ApplicationByIdRecruiter(Long idApplication, LocalDateTime applicationDate, String commentsCandidate, ApplicationStatus status, Long idVacancy, String nameVacancy, Long idCandidate, String nameCandidate, String surnameCandidate) {
        this.idApplication = idApplication;
        this.applicationDate = applicationDate;
        this.commentsCandidate = commentsCandidate;
        this.status = status;
        this.idVacancy = idVacancy;
        this.nameVacancy = nameVacancy;
        this.idCandidate = idCandidate;
        this.nameCandidate = nameCandidate;
        this.surnameCandidate = surnameCandidate;
    }

    public Long getIdApplication() {
        return idApplication;
    }

    public void setIdApplication(Long idApplication) {
        this.idApplication = idApplication;
    }

    public LocalDateTime getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDateTime applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getCommentsCandidate() {
        return commentsCandidate;
    }

    public void setCommentsCandidate(String commentsCandidate) {
        this.commentsCandidate = commentsCandidate;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public Long getIdVacancy() {
        return idVacancy;
    }

    public void setIdVacancy(Long idVacancy) {
        this.idVacancy = idVacancy;
    }

    public String getNameVacancy() {
        return nameVacancy;
    }

    public void setNameVacancy(String nameVacancy) {
        this.nameVacancy = nameVacancy;
    }

    public String getNameCandidate() {
        return nameCandidate;
    }

    public void setNameCandidate(String nameCandidate) {
        this.nameCandidate = nameCandidate;
    }

    public String getSurnameCandidate() {
        return surnameCandidate;
    }

    public void setSurnameCandidate(String surnameCandidate) {
        this.surnameCandidate = surnameCandidate;
    }

    public Long getIdCandidate() {
        return idCandidate;
    }

    public void setIdCandidate(Long idCandidate) {
        this.idCandidate = idCandidate;
    }
}
