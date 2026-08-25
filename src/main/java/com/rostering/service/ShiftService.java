package com.rostering.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rostering.model.entity.Shift;
import com.rostering.repository.*;

@Service
public class ShiftService implements IShiftService{
	
	@Autowired
	private ShiftRepository shiftRepository;
	
	@Override
	public List<Shift> getAllShifts() {
		return shiftRepository.findAll();
	}
	
	@Override
	public Shift getShiftById(Integer id) {
		return shiftRepository.findById(id)
				.orElse(null);
	}
	
	@Override
	public Shift createShift(Shift shift) {
		return shiftRepository.save(shift);
	}
	
	@Override
	public void deleteShift(Integer id) {
		shiftRepository.deleteById(id);
		
	}

}
