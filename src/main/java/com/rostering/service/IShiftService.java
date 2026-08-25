package com.rostering.service;

import java.util.List;

import com.rostering.model.entity.Shift;

public interface IShiftService {
	public List<Shift> getAllShifts();
	
	public Shift getShiftById(Integer id);
	
	public Shift createShift(Shift shift);
	
	public void deleteShift(Integer id);
}
