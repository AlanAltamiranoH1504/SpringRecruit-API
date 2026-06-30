package com.example.springboot_4_initial.repositories;

import com.example.springboot_4_initial.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT c FROM Category c WHERE c.status = :status")
    public List<Category> findCategoriesByStatus(@Param("status") boolean status);

    @Query("SELECT c FROM Category c WHERE c.name = :name")
    public Optional<Category> findByName(@Param("name") String name);
}
