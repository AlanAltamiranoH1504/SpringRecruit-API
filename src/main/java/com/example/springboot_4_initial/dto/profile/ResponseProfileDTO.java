package com.example.springboot_4_initial.dto.profile;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class ResponseProfileDTO {
    private Long idProfile;
    private String profile;
    private boolean status;
}
