package com.example.Resume_system.service;


import com.example.Resume_system.Entity.Education;
import com.example.Resume_system.Entity.User;
import com.example.Resume_system.Repository.EducationRepository;
import com.example.Resume_system.Repository.UserRepository;
import com.example.Resume_system.dto.Educationdto;
import org.hibernate.engine.jdbc.connections.internal.UserSuppliedConnectionProviderImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeService {


    private final EducationRepository educationRepository;
    private final UserRepository userRepository;

    public ResumeService(EducationRepository educationRepository,UserRepository userRepository){
        this.educationRepository =educationRepository;
        this.userRepository = userRepository;
    }

    public Education createEducation(Long userId, Educationdto dto){
        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
        Education edu = new Education();
        edu.setInstitution(dto.institution());
        edu.setDegree(dto.degree());
        edu.setStartDate(dto.startDate());
        edu.setEndDate(dto.endDate());
//        edu.setDescription(dto.description());
//        edu.setUser(user);
        return educationRepository.save(edu);
    }

    public List<Education>getEducationByUser(Long userId){
        return educationRepository.findByResumeId(userId);
    }
}
