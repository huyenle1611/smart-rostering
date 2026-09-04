package com.rostering.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rostering.model.dto.EmpResponseDTO;
import com.rostering.model.entity.Employee;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("""
            SELECT new com.rostering.model.dto.EmpResponseDTO(
            e.id,
            e.firstName,
            e.lastName,
            e.email)

            FROM Employee e

                """)
    Page<EmpResponseDTO> findAllEmpDTOs(Pageable pageable);
}
