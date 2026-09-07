package com.rostering.model.dto;

import jakarta.validation.constraints.NotBlank;

public record ShiftRequestDTO(
		@NotBlank(message = "Shift's Name is required")
		String name,
		
		@NotBlank(message = "Shift's Date must be in the future")
		String date,
		
		@NotBlank(message = "Start Time is required")
		String startTime,
		
		@NotBlank(message = "End Time is required")
		String endTime
		) {
}