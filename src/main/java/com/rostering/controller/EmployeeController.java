package com.rostering.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rostering.mapper.EmpMapper;
import com.rostering.model.dto.EmpRequestDTO;
import com.rostering.model.dto.EmpResponseDTO;
import com.rostering.model.entity.Employee;
import com.rostering.repository.IEmployeeRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private final IEmployeeRepository employeeRepository;
    private final EmpMapper empMapper;

    public EmployeeController(IEmployeeRepository employeeRepository,
            EmpMapper empMapper) {
        this.employeeRepository = employeeRepository;
        this.empMapper = empMapper;
    }

    // get all employees
    @GetMapping
    public Page<EmpResponseDTO> getEmps(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return employeeRepository.findAllEmpDTOs(pageable);
    }

    // get emp by id
    @GetMapping("/{id}")
    public ResponseEntity<EmpResponseDTO> getEmpById(@PathVariable Integer id) {
        Optional<Employee> emp = employeeRepository.findById(id);
        if (emp.isPresent()) {
            Employee employee = emp.get();
            EmpResponseDTO dto = empMapper.toResponseDTO(employee);
            // 200 success
            return ResponseEntity.ok(dto);
        } else {
            // 404 not found
            return ResponseEntity.notFound().build();
        }
    }

    // create new employee
    @PostMapping
    public ResponseEntity<String> createEmp(@Valid @RequestBody EmpRequestDTO request) {
        // map requestDTO > entity
        Employee emp = empMapper.toEntity(request);
        // save emp to db
        employeeRepository.save(emp);
        // http status 
        return ResponseEntity.status(HttpStatus.CREATED).body("Employee created successfully!");
    }

    // delete employee
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmp(@PathVariable Integer id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            Employee emp = employee.get();
            employeeRepository.delete(emp);
            // return 204 no content > delete successfully
            return ResponseEntity.noContent().build();
        } else {
            // 404 not found
            return ResponseEntity.notFound().build();
        }
    }

    // update all fields of emp - PUT
    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmp(
            @PathVariable Integer id,
            @Valid @RequestBody EmpRequestDTO request) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            Employee emp = employee.get();

            // update info
            emp.setFirstName(request.firstName());
            emp.setLastName(request.lastName());
            emp.setEmail(request.email());
            emp.setMaxWeeklyHours(request.maxWeeklyHours());
            emp.setHourlyRate(request.hourlyRate());

            // save to db
            employeeRepository.save(emp);
            return ResponseEntity.ok("Employee updated successfully!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // partially update - PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<String> patchEmp(
            @PathVariable Integer id,
            @RequestBody EmpRequestDTO request) {

        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            Employee emp = employee.get();

            // update
            if (request.firstName() != null) {
                emp.setFirstName(request.firstName());
            }

            if (request.lastName() != null) {
                emp.setLastName(request.lastName());
            }

            if (request.email() != null) {
                emp.setEmail(request.email());
            }

            if (request.hourlyRate() != null) {
                emp.setHourlyRate(request.hourlyRate());
            }

            if (request.maxWeeklyHours() != null) {
                emp.setMaxWeeklyHours(request.maxWeeklyHours());
            }
            //save to db
            employeeRepository.save(emp);
            return ResponseEntity.ok("Employee updated successfully!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
