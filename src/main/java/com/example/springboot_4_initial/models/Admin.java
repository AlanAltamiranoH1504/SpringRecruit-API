package com.example.springboot_4_initial.models;

import jakarta.persistence.*;

@Entity()
@Table(name = "tbl_admins")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_admin;
    private String name_admin;
    private String lastname_admin;
    private String img_profile;
    private String cellphone;
    private String address;
    private String token_reset_password;
    private int randome_number;
    private boolean status;
    @OneToOne()
    @JoinColumn(name = "id_user")
    private User user;



    public Admin() {
    }

    public Admin(String name_admin, String lastname_admin, String img_profile, String cellphone, String address, String token_reset_password, int randome_number, boolean status, User user) {
        this.name_admin = name_admin;
        this.lastname_admin = lastname_admin;
        this.img_profile = img_profile;
        this.cellphone = cellphone;
        this.address = address;
        this.token_reset_password = token_reset_password;
        this.randome_number = randome_number;
        this.status = status;
        this.user = user;
    }

    public Admin(Long id_admin, String name_admin, String lastname_admin, String img_profile, String cellphone, String address, String token_reset_password, int randome_number, boolean status, User user) {
        this.id_admin = id_admin;
        this.name_admin = name_admin;
        this.lastname_admin = lastname_admin;
        this.img_profile = img_profile;
        this.cellphone = cellphone;
        this.address = address;
        this.token_reset_password = token_reset_password;
        this.randome_number = randome_number;
        this.status = status;
        this.user = user;
    }

    public Long getId_admin() {
        return id_admin;
    }

    public void setId_admin(Long id_admin) {
        this.id_admin = id_admin;
    }

    public String getName_admin() {
        return name_admin;
    }

    public void setName_admin(String name_admin) {
        this.name_admin = name_admin;
    }

    public String getLastname_admin() {
        return lastname_admin;
    }

    public void setLastname_admin(String lastname_admin) {
        this.lastname_admin = lastname_admin;
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

    public String getToken_reset_password() {
        return token_reset_password;
    }

    public void setToken_reset_password(String token_reset_password) {
        this.token_reset_password = token_reset_password;
    }

    public int getRandome_number() {
        return randome_number;
    }

    public void setRandome_number(int randome_number) {
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
}
