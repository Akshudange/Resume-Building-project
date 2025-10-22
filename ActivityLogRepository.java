package com.example.Resume_system.Repository;

import com.example.Resume_system.Entity.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {


    List<ActivityLog> findByType(String type);

    // Find a single log by title
    Optional<ActivityLog> findByTitle(String title);

    // Find all logs belonging to a specific resume
    List<ActivityLog> findByResumeId(Long resumeId);

}
