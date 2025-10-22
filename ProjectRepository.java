package com.example.Resume_system.Repository;

import com.example.Resume_system.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project ,Long> {
    Optional<Project> findByTitle(String title);

    // Find all projects belonging to a specific resume
    List<Project> findByResumeId(Long resumeId);
}
