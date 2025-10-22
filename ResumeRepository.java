package com.example.Resume_system.Repository;

import com.example.Resume_system.Entity.Resume;
import com.example.Resume_system.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume , Long> {
    Optional<Resume> findByOwner(User owner);
}
