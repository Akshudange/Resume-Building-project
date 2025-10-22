package com.example.Resume_system.controller;

import com.example.Resume_system.Entity.Education;
import com.example.Resume_system.dto.Educationdto;
import com.example.Resume_system.service.ResumeService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/education")
public class EducationController {
    private final ResumeService resumeService;

    public EducationController(ResumeService resumeService){
        this.resumeService = resumeService;
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<Education>addEducation(@PathVariable Long userId,
                                                 @Valid @RequestBody Educationdto dto ){
        Education created = resumeService.createEducation(userId,dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Education>>getEducation(@PathVariable Long userId){
        return ResponseEntity.ok(resumeService.getEducationByUser(userId));
    }
}
