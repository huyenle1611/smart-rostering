package com.rostering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rostering.model.entity.Shift;

public interface IShiftRepository extends JpaRepository<Shift, Integer> {
	
}
