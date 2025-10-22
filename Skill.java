package com.example.Resume_system.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "skills")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String name;
    private String level;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id")

    private Resume resume;
}

