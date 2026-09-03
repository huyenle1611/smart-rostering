package com.rostering.mapper;

import org.mapstruct.Mapper;

import com.rostering.model.dto.ShiftRequestDTO;
import com.rostering.model.dto.ShiftResponseDTO;
import com.rostering.model.entity.Shift;

@Mapper(componentModel = "spring")
public interface ShiftMapper {
	Shift toEntity(ShiftRequestDTO dto);
	ShiftResponseDTO toResponseDTO(Shift shift);
}