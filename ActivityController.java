package com.example.Resume_system.controller;

import com.example.Resume_system.Entity.ActivityLog;
import com.example.Resume_system.Entity.Resume;
import com.example.Resume_system.Entity.User;
import com.example.Resume_system.Repository.ActivityLogRepository;
import com.example.Resume_system.Repository.ResumeRepository;
import com.example.Resume_system.Repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    private final ActivityLogRepository activityRepo;
    private final ResumeRepository resumeRepo;
    private final UserRepository userRepository;

    public ActivityController(ActivityLogRepository activityRepo, ResumeRepository resumeRepo, UserRepository userRepository) {
        this.activityRepo = activityRepo;
        this.resumeRepo = resumeRepo;
        this.userRepository = userRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addActivity(@AuthenticationPrincipal UserDetails userDetails,
                                         @RequestBody ActivityLog activity) {
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        Resume resume = resumeRepo.findByOwner(user).orElseGet(() -> {
            Resume r = new Resume();
            r.setOwner(user);
            resumeRepo.save(r);
            return r;
        });

        activity.setResume(resume);
        activityRepo.save(activity);

        // Auto-update resume: for example, if type == COURSE -> add skill/project etc.
        // Here we simply append the activity to resume.activities (DB will manage)
        resume.getActivities().add(activity);
        resumeRepo.save(resume);

        return ResponseEntity.ok(activity);
    }
}
