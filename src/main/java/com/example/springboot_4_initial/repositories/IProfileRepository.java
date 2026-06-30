package com.example.springboot_4_initial.repositories;

import com.example.springboot_4_initial.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProfileRepository extends JpaRepository<Profile, Long> {
    @Query("SELECT p FROM Profile p WHERE p.status = :status")
    public abstract List<Profile> findAllByStatus(@Param("status") boolean status);

    @Query("SELECT p FROM Profile p WHERE p.profile = :profile")
    public abstract Profile findByName(@Param("profile") String profile);
}
