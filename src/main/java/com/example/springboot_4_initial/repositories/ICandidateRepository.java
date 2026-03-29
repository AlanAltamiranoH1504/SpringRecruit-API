package com.example.springboot_4_initial.repositories;

import com.example.springboot_4_initial.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ICandidateRepository extends JpaRepository<Candidate, Long> {
    @Query("SELECT c FROM Candidate c WHERE c.status = :status")
    public abstract List<Candidate> candidates_by_status(@Param("status") boolean status);

    @Query("SELECT c FROM Candidate c WHERE c.token_confirm_account = :token")
    public abstract Candidate get_candidate_by_token_confirm_account(@Param("token") String token);

    @Query("SELECT c FROM Candidate c WHERE c.token_reset_password = :token AND c.randome_number = :random")
    public abstract Optional<Candidate> candidateToResetPassword(@Param("token") String token, @Param("random") String random);
}
