package com.example.springboot_4_initial.repositories;

import com.example.springboot_4_initial.models.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IRecruiterRepository extends JpaRepository<Recruiter, Long> {
    @Query("SELECT r FROM Recruiter r WHERE r.username = :username")
    public abstract Recruiter username_in_use(@Param("username") String username);

    @Query("SELECT r FROM Recruiter r WHERE r.token_confirm_account = :token")
    public abstract Recruiter get_recruiter_by_token_account(@Param("token") String token);

    @Query("SELECT r FROM Recruiter r WHERE r.status = :status")
    public abstract List<Recruiter> list_recruiters(@Param("status") boolean status);

    @Query("SELECT r FROM Recruiter r WHERE r.token_reset_password = :token AND r.randome_number = :random")
    public abstract Optional<Recruiter> recruiterToResetPassword(@Param("token") String token, @Param("random") String random);
}
