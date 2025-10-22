package com.example.Resume_system.Entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "activity_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ActivityLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String type; // INTERNSHIP / HACKATHON / COURSE / PROJECT_VERIFICATION / SKILL_TEST
    private String title;
    private String description;
    private String date; // ISO date string

    // link to external verification or certificate
    private String verificationLink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id")
    private Resume resume;
}
