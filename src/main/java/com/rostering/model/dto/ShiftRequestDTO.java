package com.rostering.model.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;

public record ShiftRequestDTO(
		@NotBlank(message = "Shift's Name is required")
		String name,
		
		@Future(message = "Shift's Date must be in the future")
		LocalDate date,
		
		@NotBlank(message = "Start Time is required")
		LocalTime startTime,
		
		@NotBlank(message = "End Time is required")
		LocalTime endTime
		) {
}
