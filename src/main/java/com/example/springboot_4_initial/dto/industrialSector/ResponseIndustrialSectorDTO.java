package com.example.springboot_4_initial.dto.industrialSector;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
@JsonPropertyOrder({"idIndustrialSector", "nameIndustrialSector", "descriptionIndustrialSector", "status"})
public class ResponseIndustrialSectorDTO {
    private Long idIndustrialSector;
    private String nameIndustrialSector;
    private String descriptionIndustrialSector;
    private boolean status;
}
