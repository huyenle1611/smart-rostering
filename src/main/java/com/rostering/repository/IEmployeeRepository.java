package com.rostering.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rostering.model.entity.Employee;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {

}
