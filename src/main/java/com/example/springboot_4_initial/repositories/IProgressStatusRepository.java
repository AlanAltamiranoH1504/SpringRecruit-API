package com.example.springboot_4_initial.repositories;

import com.example.springboot_4_initial.models.ProgressStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProgressStatusRepository extends JpaRepository<ProgressStatus, Long> {
    @Query("SELECT p FROM ProgressStatus p WHERE p.status = :status")
    public abstract List<ProgressStatus> findAllByStatus(@Param("status") boolean status);

    @Query("SELECT p FROM ProgressStatus p WHERE p.name_progress_status = :name")
    public abstract ProgressStatus findByName(@Param("name") String name);
}
