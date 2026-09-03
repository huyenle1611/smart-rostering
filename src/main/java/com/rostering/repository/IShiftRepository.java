package com.rostering.repository;

import com.rostering.model.entity.Shift;


import org.springframework.data.jpa.repository.JpaRepository;

public interface IShiftRepository extends JpaRepository<Shift, Integer> {
	
}
