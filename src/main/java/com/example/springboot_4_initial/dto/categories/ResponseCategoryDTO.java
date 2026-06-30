package com.example.springboot_4_initial.dto.categories;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Builder
@JsonPropertyOrder({"idCategory", "name", "description"})
public class ResponseCategoryDTO {
    private Long idCategory;
    private String name;
    private String description;
    private boolean status;
}
