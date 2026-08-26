package com.rostering.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rostering.model.entity.Shift;
import com.rostering.repository.*;

@Service
public class ShiftService{
	
	@Autowired
	private ShiftRepository shiftRepository;
	
	public List<Shift> getAllShifts() {
		return shiftRepository.findAll();
	}
	
	public Shift getShiftById(Integer id) {
		return shiftRepository.findById(id)
				.orElse(null);
	}
	
	public Shift createShift(Shift shift) {
		return shiftRepository.save(shift);
	}
	
	public void deleteShift(Integer id) {
		shiftRepository.deleteById(id);
		
	}

}
