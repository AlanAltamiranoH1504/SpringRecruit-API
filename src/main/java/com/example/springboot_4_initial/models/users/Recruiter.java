package com.example.springboot_4_initial.models.users;

import com.example.springboot_4_initial.models.Vacancy;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity()
@Table(name = "tbl_tbl_recruiter")
@JsonPropertyOrder({"id_recruiter", "name", "surnames", "username", "img_profile", "token_confirm_account", "token_reset_password", "randome_number", "status", "user"})
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
@Builder
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
    private List<Vacancy> vacancies = new ArrayList<>();
}
