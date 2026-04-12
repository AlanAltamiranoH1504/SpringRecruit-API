package com.example.springboot_4_initial.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_categories")
@JsonPropertyOrder({"id", "name", "description", "status"})
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private boolean status;
    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Vacancy> vacancies = new ArrayList<>();

    public Category() {
    }

    public Category(String name, String description, boolean status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public Category(Long id, String name, String description, boolean status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
