package com.example.springboot_4_initial.models;

import com.example.springboot_4_initial.models.enums.ApplicationStatus;
import com.example.springboot_4_initial.models.users.Candidate;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity()
@Table(name = "tbl_applications")
@JsonPropertyOrder({"id_application", "application_date", "last_update", "url_cv", "comments_candidate", "notes_recruiter",
"status", "vacancy", "candidate"})
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_application;
    private LocalDateTime application_date;
    private LocalDateTime last_update;
    private String url_cv;
    private String comments_candidate;
    private String notes_recruiter;
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status = ApplicationStatus.RECEIVED;
    @ManyToOne()
    @JoinColumn(name = "id_vacancy")
    private Vacancy vacancy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_candidate")
    private Candidate candidate;

    public Application() {
    }

    public Application(LocalDateTime application_date, LocalDateTime last_update, String url_cv, String comments_candidate, String notes_recruiter, ApplicationStatus status, Vacancy vacancy, Candidate candidate) {
        this.application_date = application_date;
        this.last_update = last_update;
        this.url_cv = url_cv;
        this.comments_candidate = comments_candidate;
        this.notes_recruiter = notes_recruiter;
        this.status = status;
        this.vacancy = vacancy;
        this.candidate = candidate;
    }

    public Long getId_application() {
        return id_application;
    }

    public void setId_application(Long id_application) {
        this.id_application = id_application;
    }

    public LocalDateTime getApplication_date() {
        return application_date;
    }

    public void setApplication_date(LocalDateTime application_date) {
        this.application_date = application_date;
    }

    public LocalDateTime getLast_update() {
        return last_update;
    }

    public void setLast_update(LocalDateTime last_update) {
        this.last_update = last_update;
    }

    public String getUrl_cv() {
        return url_cv;
    }

    public void setUrl_cv(String url_cv) {
        this.url_cv = url_cv;
    }

    public String getComments_candidate() {
        return comments_candidate;
    }

    public void setComments_candidate(String comments_candidate) {
        this.comments_candidate = comments_candidate;
    }

    public String getNotes_recruiter() {
        return notes_recruiter;
    }

    public void setNotes_recruiter(String notes_recruiter) {
        this.notes_recruiter = notes_recruiter;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }
}
