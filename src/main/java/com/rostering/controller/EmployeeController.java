package com.rostering.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public List<EmpResponseDTO> getEmployees() {
        List<Employee> emps = employeeRepository.findAll();
        List<EmpResponseDTO> empDTOs = new ArrayList<>();

        for(Employee emp : emps){
            EmpResponseDTO empDTO = empMapper.toResponseDTO(emp);
            empDTOs.add(empDTO);
        }
        return empDTOs;
    }

    // get emp by id
    @GetMapping("/{id}")
    public ResponseEntity<EmpResponseDTO> getEmployeeById(@PathVariable Integer id) {
        Optional<Employee> emp = employeeRepository.findById(id);
        if (emp.isPresent()) {
            Employee employee = emp.get();
            EmpResponseDTO dto = empMapper.toResponseDTO(employee);
            //200 success
            return ResponseEntity.ok(dto);
        } else {
            //404 not found
            return ResponseEntity.notFound().build();
        }
    }
    // create new employee
    @PostMapping
    public ResponseEntity<EmpResponseDTO> createEmployee(@Valid @RequestBody EmpRequestDTO request){
        //map requestDTO > entity
        Employee emp = empMapper.toEntity(request);
        //save emp to db
        Employee savedEmp = employeeRepository.save(emp);
        //map savedEmp to empResponseDTO
        EmpResponseDTO empDTO = empMapper.toResponseDTO(savedEmp);
        //return http status + dto 
        return ResponseEntity.status(HttpStatus.CREATED).body(empDTO);
    }

    // delete employee
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id){
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()){
            Employee emp = employee.get();
            employeeRepository.delete(emp);
            //return 204 no content > delete successfully
            return ResponseEntity.noContent().build();
        } else {
            //404 not found
            return ResponseEntity.notFound().build();
        }
    }

}
