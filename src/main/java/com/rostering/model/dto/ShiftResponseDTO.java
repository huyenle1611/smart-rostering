package com.rostering.model.dto;


public record ShiftResponseDTO(
		Integer id,
		String name,
		String date,
		String startTime,
		String endTime) {
}