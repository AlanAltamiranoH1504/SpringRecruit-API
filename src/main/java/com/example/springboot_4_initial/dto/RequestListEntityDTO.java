package com.example.springboot_4_initial.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestListEntityDTO {
    @NotNull(message = "Status Required")
    private Boolean status;
}
