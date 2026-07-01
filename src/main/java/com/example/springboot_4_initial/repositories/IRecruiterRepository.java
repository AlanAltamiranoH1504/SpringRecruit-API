package com.example.springboot_4_initial.repositories;

import com.example.springboot_4_initial.models.users.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRecruiterRepository extends JpaRepository<Recruiter, Long> {
}
