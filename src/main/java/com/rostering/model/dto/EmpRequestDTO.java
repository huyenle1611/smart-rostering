package com.rostering.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record EmpRequestDTO(
        @NotBlank(message = "First name is required")
        String firstName,
        
        @NotBlank(message = "Last name is required")
        String lastName,
        
        @NotBlank(message = "Email is required")
        @Email(message = "Email is invalid")
        String email,

        @NotNull(message = "Hourly rate is required")
        @Positive(message = "Hourly rate must be greater than 0")
        Double hourlyRate,

        @NotNull(message = "Max weekly hours is required")
        @Positive(message = "Max weekly hours must be greater than 0")
        Integer maxWeeklyHours) {
}
