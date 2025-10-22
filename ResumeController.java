package com.example.Resume_system.controller;


import com.example.Resume_system.Entity.Resume;
import com.example.Resume_system.Entity.User;
import com.example.Resume_system.Repository.ResumeRepository;
import com.example.Resume_system.Repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;

    public ResumeController(ResumeRepository resumeRepository, UserRepository userRepository) {
        this.resumeRepository = resumeRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMyResume(@AuthenticationPrincipal UserDetails userDetails) {
        var email = userDetails.getUsername();
        var user = userRepository.findByEmail(email).orElseThrow();
        var resume = resumeRepository.findByOwner(user).orElseGet(() -> {
            Resume r = new Resume();
            r.setOwner(user);
            resumeRepository.save(r);
            return r;
        });
        return ResponseEntity.ok(resume);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateResume(@AuthenticationPrincipal UserDetails userDetails, @RequestBody Resume input) {
        var user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        var resume = resumeRepository.findByOwner(user).orElse(new Resume());
        // map allowed fields
        resume.setOwner(user);
        resume.setHeadline(input.getHeadline());
        resume.setSummary(input.getSummary());
        // For nested lists you'd update items properly; for starter replace them:
        resume.setSkills(input.getSkills());
        resume.setProjects(input.getProjects());
        resume.setExperiences(input.getExperiences());
        resume.setEducations(input.getEducations());
        resumeRepository.save(resume);
        return ResponseEntity.ok(resume);
    }
}
