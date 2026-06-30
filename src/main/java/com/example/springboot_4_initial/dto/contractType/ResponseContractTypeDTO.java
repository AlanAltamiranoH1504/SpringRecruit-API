package com.example.springboot_4_initial.dto.contractType;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class ResponseContractTypeDTO {
    private Long idContractType;
    private String nameContractType;
    private Boolean status;
}
