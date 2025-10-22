package com.example.Resume_system.Repository;

import com.example.Resume_system.Entity.Verification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VerificationRepository extends JpaRepository<Verification, Long> {
    Optional<Verification> findByExternalId(String externalId);

    // Find all verifications by status (e.g., PENDING, VERIFIED, REJECTED)
    List<Verification> findByStatus(String status);

    // Find all verifications associated with a specific resume
    List<Verification> findByResumeId(Long resumeId);
}
