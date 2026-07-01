package com.example.springboot_4_initial.models.users;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

@Entity()
@Table(name = "tbl_candidates")
@JsonPropertyOrder({
        "id_candidate", "name_candidate", "lastname_candidate",
        "img_profile", "public_id_img", "cellphone", "address", "token_confirm_account",
        "token_reset_password", "randome_number", "status", "user"})
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_candidate;
    private String name_candidate;
    private String lastname_candidate;
    private String img_profile;
    private String public_id_img;
    private String cellphone;
    private String address;
    private String token_confirm_account;
    private String token_reset_password;
    private String randome_number;
    private boolean status;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User user;

    public Candidate() {
    }

    public Candidate(String name_candidate, String lastname_candidate, String img_profile, String public_id_img, String cellphone, String address, String token_confirm_account, String token_reset_password, String randome_number, boolean status, User user) {
        this.name_candidate = name_candidate;
        this.lastname_candidate = lastname_candidate;
        this.img_profile = img_profile;
        this.public_id_img = public_id_img;
        this.cellphone = cellphone;
        this.address = address;
        this.token_confirm_account = token_confirm_account;
        this.token_reset_password = token_reset_password;
        this.randome_number = randome_number;
        this.status = status;
        this.user = user;
    }

    public Candidate(Long id_candidate, String name_candidate, String lastname_candidate, String img_profile, String public_id_img, String cellphone, String address, String token_confirm_account, String token_reset_password, String randome_number, boolean status, User user) {
        this.id_candidate = id_candidate;
        this.name_candidate = name_candidate;
        this.lastname_candidate = lastname_candidate;
        this.img_profile = img_profile;
        this.public_id_img = public_id_img;
        this.cellphone = cellphone;
        this.address = address;
        this.token_confirm_account = token_confirm_account;
        this.token_reset_password = token_reset_password;
        this.randome_number = randome_number;
        this.status = status;
        this.user = user;
    }

    public Long getId_candidate() {
        return id_candidate;
    }

    public void setId_candidate(Long id_candidate) {
        this.id_candidate = id_candidate;
    }

    public String getName_candidate() {
        return name_candidate;
    }

    public void setName_candidate(String name_candidate) {
        this.name_candidate = name_candidate;
    }

    public String getLastname_candidate() {
        return lastname_candidate;
    }

    public void setLastname_candidate(String lastname_candidate) {
        this.lastname_candidate = lastname_candidate;
    }

    public String getImg_profile() {
        return img_profile;
    }

    public void setImg_profile(String img_profile) {
        this.img_profile = img_profile;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getPublic_id_img() {
        return public_id_img;
    }

    public void setPublic_id_img(String public_id_img) {
        this.public_id_img = public_id_img;
    }
}
