package com.example.springboot_4_initial.dto.recruiter;

public class RecruiterInSessionDTO {
    private String name;
    private String surnames;
    private String username;
    private String img_profile;

    public RecruiterInSessionDTO(String name, String surnames, String username, String img_profile) {
        this.name = name;
        this.surnames = surnames;
        this.username = username;
        this.img_profile = img_profile;
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
}
