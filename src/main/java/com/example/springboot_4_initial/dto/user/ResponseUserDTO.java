package com.example.springboot_4_initial.dto.user;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
@JsonPropertyOrder({"idUser", "name", "surnames", "email", "username", "imageProfile", "status"})
public class ResponseUserDTO {
    private Long idUser;
    private String name;
    private String surnames;
    private String email;
    private String username;
    private String imageProfile;
    private boolean status;
}
