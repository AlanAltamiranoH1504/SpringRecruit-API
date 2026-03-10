package com.example.springboot_4_initial.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

import java.util.List;

@Entity()
@Table(name = "tbl_tbl_recruiter")
@JsonPropertyOrder({"id_recruiter", "name", "surnames", "username", "img_profile", "token_confirm_account", "token_reset_password", "randome_number", "status", "user"})
public class Recruiter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_recruiter;
    private String name;
    private String surnames;
    private String username;
    private String img_profile;
    private String token_confirm_account;
    private String token_reset_password;
    private String randome_number;
    private boolean status;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User user;

    // ! Un reclutador puede tener muchas vacantes
    @OneToMany(mappedBy = "recruiter")
    @JsonIgnore
    private List<Vacancy> vacancies;

    public Recruiter() {
    }

    public Recruiter(String name, String surnames, String username, String img_profile, String token_confirm_account, String token_reset_password, String randome_number, boolean status, User user) {
        this.name = name;
        this.surnames = surnames;
        this.username = username;
        this.img_profile = img_profile;
        this.token_confirm_account = token_confirm_account;
        this.token_reset_password = token_reset_password;
        this.randome_number = randome_number;
        this.status = status;
        this.user = user;
    }

    public Recruiter(Long id_recruiter, String name, String surnames, String username, String img_profile, String token_confirm_account, String token_reset_password, String randome_number, boolean status, User user) {
        this.id_recruiter = id_recruiter;
        this.name = name;
        this.surnames = surnames;
        this.username = username;
        this.img_profile = img_profile;
        this.token_confirm_account = token_confirm_account;
        this.token_reset_password = token_reset_password;
        this.randome_number = randome_number;
        this.status = status;
        this.user = user;
    }



    public Long getId_recruiter() {
        return id_recruiter;
    }

    public void setId_recruiter(Long id_recruiter) {
        this.id_recruiter = id_recruiter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImg_profile() {
        return img_profile;
    }

    public void setImg_profile(String img_profile) {
        this.img_profile = img_profile;
    }

    public String getToken_confirm_account() {
        return token_confirm_account;
    }

    public void setToken_confirm_account(String token_confirm_account) {
        this.token_confirm_account = token_confirm_account;
    }

    public String getToken_reset_password() {
        return token_reset_password;
    }

    public void setToken_reset_password(String token_reset_password) {
        this.token_reset_password = token_reset_password;
    }

    public String getRandome_number() {
        return randome_number;
    }

    public void setRandome_number(String randome_number) {
        this.randome_number = randome_number;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Vacancy> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }
}
