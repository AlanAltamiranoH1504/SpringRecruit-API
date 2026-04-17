package com.example.springboot_4_initial.dto.recruiter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class SendMailToCandidateDTO {

    @NotBlank(message = "El nombre del candidato es requerido")
    private String nameCandidate;

    @NotBlank(message = "El email del candidato es requerido")
    @Email(message = "El email del candidato no es valido")
    private String emailCandidate;

    @NotBlank(message = "El nombre de la vacante es requerido")
    private String nameVacancy;

    @NotBlank(message = "El body del email es requerido")
    private String bodyEmail;

    @NotBlank(message = "El token jwt es requerido")
    private String tokenJWT;

    public String getNameCandidate() {
        return nameCandidate;
    }

    public void setNameCandidate(String namaCandidate) {
        this.nameCandidate = namaCandidate;
    }

    public String getEmailCandidate() {
        return emailCandidate;
    }

    public void setEmailCandidate(String emailCandidate) {
        this.emailCandidate = emailCandidate;
    }

    public String getNameVacancy() {
        return nameVacancy;
    }

    public void setNameVacancy(String nameVacancy) {
        this.nameVacancy = nameVacancy;
    }

    public String getBodyEmail() {
        return bodyEmail;
    }

    public void setBodyEmail(String bodyEmail) {
        this.bodyEmail = bodyEmail;
    }

    public String getTokenJWT() {
        return tokenJWT;
    }

    public void setTokenJWT(String tokenJWT) {
        this.tokenJWT = tokenJWT;
    }
}
