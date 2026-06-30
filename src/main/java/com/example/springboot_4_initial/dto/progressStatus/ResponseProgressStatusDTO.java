package com.example.springboot_4_initial.dto.progressStatus;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Builder
@JsonPropertyOrder({"idProgressStatus", "nameProgressStatus", "descriptionProgressStatus", "status"})
public class ResponseProgressStatusDTO {
    private Long idProgressStatus;
    private String nameProgressStatus;
    private String descriptionProgressStatus;
    private Boolean status;
}
