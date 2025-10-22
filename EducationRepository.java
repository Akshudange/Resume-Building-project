package com.example.Resume_system.Repository;

import com.example.Resume_system.Entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EducationRepository extends JpaRepository<Education, Long> {
    Optional<Education> findByInstitution(String institution);

    List<Education> findByResumeId(Long resumeId);

}
