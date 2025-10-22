package com.example.Resume_system.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name ="erification")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Verification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String provider; // e.g. "HackathonPlatform", "Coursera"
    private String externalId;
    private String status; // PENDING, VERIFIED, REJECTED
    private String details;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id")
    private Resume resume;
}
