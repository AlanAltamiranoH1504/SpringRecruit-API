package com.example.springboot_4_initial.dto.user.recruiter;

import lombok.*;

@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Builder
public class RequestRecruiterDTO {
    private String name;
    private String surnames;
    private String username;
    private String img_profile;
}
