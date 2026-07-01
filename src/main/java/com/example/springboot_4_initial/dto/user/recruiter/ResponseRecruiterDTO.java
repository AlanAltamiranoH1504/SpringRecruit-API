package com.example.springboot_4_initial.dto.user.recruiter;

import com.example.springboot_4_initial.models.users.User;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class ResponseRecruiterDTO {
    private Long idRecruiter;
    private String name;
    private String surnames;
    private String username;
    private String imgProfile;
    private String tokenConfirmAccount;
    private String tokenResetPasword;
    private String randomeNumber;
    private boolean status;
    private User user;
}
