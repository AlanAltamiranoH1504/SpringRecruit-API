package com.example.springboot_4_initial.repositories;

import com.example.springboot_4_initial.models.WorkModality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IWorkModalityRepository extends JpaRepository<WorkModality, Long> {

    @Query("SELECT w FROM WorkModality w WHERE w.status = :status")
    public abstract List<WorkModality> listByStatus(@Param("status") boolean status);
}
