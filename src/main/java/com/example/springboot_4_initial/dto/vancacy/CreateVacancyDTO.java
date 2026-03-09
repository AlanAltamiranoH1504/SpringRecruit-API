package com.example.springboot_4_initial.dto.vancacy;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateVacancyDTO {
    @NotBlank(message = "El id del usuario es requerido")
    private String id_user_crypt;

    @NotBlank(message = "El nombre de la vacante es requerido")
    @Length(max = 150, message = "El nombre no puede tener mas de 150 caracteres")
    private String name;

    @NotBlank(message = "La descripcion de la vacante es requerida")
    @Length(max = 5000, message = "La descripcio  no puede tener mas de 5000 caracteres")
    private String description;

    @NotBlank(message = "La localizacion es requerida")
    @Length(max = 250, message = "La localizacion no puede tener mas de 250 caracteres")
    private String location;

    @NotNull(message = "El salario es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El salario debe ser mayor a 0")
    @Digits(integer = 7, fraction = 2, message = "El formato del salario es inválido (máximo 7 enteros y 2 decimales)")
    private BigDecimal salary;

    @NotNull(message = "La fecha de termino de la vacante es requerida")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate finish_date;

    @NotEmpty(message = "Los requisitos de la vacante es requerida")
    @Length(max = 5000, message = "Los requisitos no pueden ser mayor a 5000 caracteres")
    private String requirements;

    @NotEmpty(message = "Las responsabilidades de la vacante es requerida")
    @Length(max = 5000, message = "Las responsabilidades no pueden ser mayor a 5000 caracteres")
    private String responsibilities;

    @NotNull(message = "El tipo de contrato es requerido")
    @Range(min = 1, message = "Tipo de contrato no valido")
    private Long idContract_type;

    @NotNull(message = "El estado de la vacante es requerido")
    @Range(min = 1, message = "Estado de vacante no valido")
    private Long idProgressStatus;

    @NotNull(message = "El sector industrial de la vacante es requerida")
    @Range(min = 1, message = "Sector de la vacante no valido")
    private Long idIndustrialSector;

    @NotNull(message = "La modalidad de trabajo requerida")
    @Range(min = 1, message = "Modalidad de trabajo no valida")
    private Long idWorkModality;

    @NotNull(message = "La categoria es requerida")
    @Range(min = 1, message = "Categoria no valida")
    private Long idCategory;

    public String getId_user_crypt() {
        return id_user_crypt;
    }

    public void setId_user_crypt(String id_user_crypt) {
        this.id_user_crypt = id_user_crypt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }


    public LocalDate getFinish_date() {
        return finish_date;
    }

    public void setFinish_date(LocalDate finish_date) {
        this.finish_date = finish_date;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public Long getIdContract_type() {
        return idContract_type;
    }

    public void setIdContract_type(Long idContract_type) {
        this.idContract_type = idContract_type;
    }

    public Long getIdProgressStatus() {
        return idProgressStatus;
    }

    public void setIdProgressStatus(Long idProgressStatus) {
        this.idProgressStatus = idProgressStatus;
    }

    public Long getIdIndustrialSector() {
        return idIndustrialSector;
    }

    public void setIdIndustrialSector(Long idIndustrialSector) {
        this.idIndustrialSector = idIndustrialSector;
    }

    public Long getIdWorkModality() {
        return idWorkModality;
    }

    public void setIdWorkModality(Long idWorkModality) {
        this.idWorkModality = idWorkModality;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }
}
