package com.example.springboot_4_initial.repositories;

import com.example.springboot_4_initial.models.IndustrialSector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IIndustrialSectorRepository extends JpaRepository<IndustrialSector, Long> {
    @Query("SELECT i FROM IndustrialSector i WHERE i.status = :status")
    public List<IndustrialSector> findAll(@Param("status") boolean status);

    @Query("SELECT i FROM IndustrialSector i WHERE i.name_industrial_sector = :nameIndustrialSector")
    public Optional<IndustrialSector> findByName(@Param("nameIndustrialSector") String nameIndustrialSector);
}
