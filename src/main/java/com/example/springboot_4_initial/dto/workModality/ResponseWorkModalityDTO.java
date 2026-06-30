package com.example.springboot_4_initial.dto.workModality;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
@JsonPropertyOrder({"idWorkModality", "nameWorkModality", "status"})
public class ResponseWorkModalityDTO {
    private Long idWorkModality;
    private String nameWorkModality;
    private boolean status;
}
