package com.example.springboot_4_initial.dto.application;

import com.example.springboot_4_initial.models.enums.ApplicationStatus;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;

@JsonPropertyOrder({
        "idApplication", "applicationDate", "notesCandidate", "statusApplication", "urlCV",
        "idCandidate", "nameCandidate", "surnameCandidate", "emailCandidate", "cellphoneCandidate", "imgProfileCandidate"
})
public class ApplicationDetailsDTO {
    private Long idApplication;
    private LocalDateTime applicationDate;
    private String notesCandidate;
    private ApplicationStatus statusApplication;
    private String urlCV;

    private Long idCandidate;
    private String nameCandidate;
    private String surnameCandidate;
    private String emailCandidate;
    private String cellphoneCandidate;
    private String imgProfileCandidate;

    private Long idVacancy;
    private String nameVacancy;

    public ApplicationDetailsDTO(Long idApplication, LocalDateTime applicationDate, String notesCandidate, ApplicationStatus statusApplication, String urlCV, Long idCandidate, String nameCandidate, String surnameCandidate, String emailCandidate, String cellphoneCandidate, String imgProfileCandidate, Long idVacancy, String nameVacancy) {
        this.idApplication = idApplication;
        this.applicationDate = applicationDate;
        this.notesCandidate = notesCandidate;
        this.statusApplication = statusApplication;
        this.urlCV = urlCV;
        this.idCandidate = idCandidate;
        this.nameCandidate = nameCandidate;
        this.surnameCandidate = surnameCandidate;
        this.emailCandidate = emailCandidate;
        this.cellphoneCandidate = cellphoneCandidate;
        this.imgProfileCandidate = imgProfileCandidate;
        this.idVacancy = idVacancy;
        this.nameVacancy = nameVacancy;
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

    public String getNotesCandidate() {
        return notesCandidate;
    }

    public void setNotesCandidate(String notesCandidate) {
        this.notesCandidate = notesCandidate;
    }

    public ApplicationStatus getStatusApplication() {
        return statusApplication;
    }

    public void setStatusApplication(ApplicationStatus statusApplication) {
        this.statusApplication = statusApplication;
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

    public String getSurnameCandidate() {
        return surnameCandidate;
    }

    public void setSurnameCandidate(String surnameCandidate) {
        this.surnameCandidate = surnameCandidate;
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

    public String getUrlCV() {
        return urlCV;
    }

    public void setUrlCV(String urlCV) {
        this.urlCV = urlCV;
    }

    public String getImgProfileCandidate() {
        return imgProfileCandidate;
    }

    public void setImgProfileCandidate(String imgProfileCandidate) {
        this.imgProfileCandidate = imgProfileCandidate;
    }
}
