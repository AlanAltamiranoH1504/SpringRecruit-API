package com.example.springboot_4_initial.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity()
@Table(name = "tbl_applications")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_application;
    private LocalDateTime application_date;
    private LocalDateTime last_update;
    private String url_cv;
    private String comments_candidate;
    private String notes_recruiter;
    @ManyToOne()
    @JoinColumn(name = "id_vacancy")
    private Vacancy vacancy;

    @ManyToOne()
    @JoinColumn(name = "id_candidate")
    private Candidate candidate;

    public Application() {
    }

    public Application(LocalDateTime application_date, LocalDateTime last_update, String url_cv, String comments_candidate, String notes_recruiter, Vacancy vacancy, Candidate candidate) {
        this.application_date = application_date;
        this.last_update = last_update;
        this.url_cv = url_cv;
        this.comments_candidate = comments_candidate;
        this.notes_recruiter = notes_recruiter;
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
}
