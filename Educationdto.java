package com.example.Resume_system.dto;

import jakarta.validation.constraints.NotBlank;

public record Educationdto(
        Long id,
        @NotBlank String institution,
        @NotBlank String degree,
        String startDate,
        String endDate,
        String description
) {
}
