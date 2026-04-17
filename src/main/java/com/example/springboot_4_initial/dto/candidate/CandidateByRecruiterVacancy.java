package com.example.springboot_4_initial.dto.candidate;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDate;

@JsonPropertyOrder({
        "idCandidate", "nameCandidate", "lastnameCandidate", "emailCandidate", "cellphoneCandidate",
        "idVacancy", "nameVacancy", "locationVacancy", "publicationDate"
})
public class CandidateByRecruiterVacancy {
    Long idCandidate;
    String nameCandidate;
    String lastnameCandidate;
    String emailCandidate;
    String cellphoneCandidate;

    Long idVacancy;
    String nameVacancy;
    String locationVacancy;
    LocalDate publicationDate;

    public CandidateByRecruiterVacancy() {
    }

    public CandidateByRecruiterVacancy(Long idCandidate, String nameCandidate, String lastnameCandidate, String emailCandidate, String cellphoneCandidate, Long idVacancy, String nameVacancy, String locationVacancy, LocalDate publicationDate) {
        this.idCandidate = idCandidate;
        this.nameCandidate = nameCandidate;
        this.lastnameCandidate = lastnameCandidate;
        this.emailCandidate = emailCandidate;
        this.cellphoneCandidate = cellphoneCandidate;
        this.idVacancy = idVacancy;
        this.nameVacancy = nameVacancy;
        this.locationVacancy = locationVacancy;
        this.publicationDate = publicationDate;
    }

    public Long getIdCandidate() {
        return idCandidate;
    }

    public void setIdCandidate(Long idCandidate) {
        this.idCandidate = idCandidate;
    }

    public String getNameCandidate() {
        return nameCandidate;
    }

    public void setNameCandidate(String nameCandidate) {
        this.nameCandidate = nameCandidate;
    }

    public String getLastnameCandidate() {
        return lastnameCandidate;
    }

    public void setLastnameCandidate(String lastnameCandidate) {
        this.lastnameCandidate = lastnameCandidate;
    }

    public String getEmailCandidate() {
        return emailCandidate;
    }

    public void setEmailCandidate(String emailCandidate) {
        this.emailCandidate = emailCandidate;
    }

    public String getCellphoneCandidate() {
        return cellphoneCandidate;
    }

    public void setCellphoneCandidate(String cellphoneCandidate) {
        this.cellphoneCandidate = cellphoneCandidate;
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

    public String getLocationVacancy() {
        return locationVacancy;
    }

    public void setLocationVacancy(String locationVacancy) {
        this.locationVacancy = locationVacancy;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }
}
