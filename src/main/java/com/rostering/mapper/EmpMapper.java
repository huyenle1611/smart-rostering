package com.rostering.mapper;

import org.mapstruct.Mapper;
import com.rostering.model.dto.EmpRequestDTO;
import com.rostering.model.dto.EmpResponseDTO;
import com.rostering.model.entity.Employee;

@Mapper(componentModel = "spring")
public interface EmpMapper {
    Employee toEntity(EmpRequestDTO dto);
    EmpResponseDTO toResponseDTO(Employee employee);

}
