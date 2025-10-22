package com.example.Resume_system.Repository;

import com.example.Resume_system.Entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    Optional<Skill> findByName(String name);

    // Find all skills associated with a specific resume
    List<Skill> findByResumeId(Long resumeId);
}
