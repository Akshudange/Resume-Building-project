package com.example.Resume_system.Repository;

import com.example.Resume_system.Entity.Experince;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExperienceRepository extends JpaRepository<Experince ,Long> {

    Optional<Experince> findByCompany(String company);

    // Find all experiences associated with a specific resume
    List<Experince> findByResumeId(Long resumeId);
}
