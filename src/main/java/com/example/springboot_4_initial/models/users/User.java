package com.example.springboot_4_initial.models.users;

import com.example.springboot_4_initial.models.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_users")
@JsonPropertyOrder({"id_user", "email", "password", "status", "profiles"})
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;
    private String email;
    @JsonIgnore
    private String password;
    private boolean status;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tbl_rel_users_profile",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_profile")
    )
    private List<Profile> profiles = new ArrayList<>();

    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Recruiter recruiter;

    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Candidate candidate;

    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Admin admin;

    private void addProfiles(Profile profile) {
        this.profiles.add(profile);
    }
}
