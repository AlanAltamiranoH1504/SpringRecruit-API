package com.example.springboot_4_initial.repositories;

import com.example.springboot_4_initial.models.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IApplicationRepository extends JpaRepository<Application, Long> {
}
