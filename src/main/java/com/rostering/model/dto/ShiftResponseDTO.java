package com.rostering.model.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record ShiftResponseDTO(
		Integer id,
		String name,
		LocalDate date,
		LocalTime startTime,
		LocalTime endTime) {
}