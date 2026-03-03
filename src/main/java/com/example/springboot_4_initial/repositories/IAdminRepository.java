package com.example.springboot_4_initial.repositories;

import com.example.springboot_4_initial.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepository extends JpaRepository<Admin, Long> {
}
