package com.rostering.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rostering.model.entity.Shift;
import com.rostering.service.ShiftService;

@RestController
@RequestMapping("/shifts")
public class ShiftController {
	
	@Autowired
	private ShiftService shiftService;
	
	@GetMapping("/")
	public String home() {
	    return "Welcome to the Shift Management API! Try accessing /shifts";
	}
	
	@GetMapping
	public List<Shift> getAllShifts() {
		return shiftService.getAllShifts();
	}
	
	@GetMapping("/{id}")
	public Shift getShiftById(@PathVariable Integer id) {
		return shiftService.getShiftById(id);
	}
	
	@PostMapping
	public Shift createShift(@RequestBody Shift shift) {
		return shiftService.createShift(shift);
	}
	
	@DeleteMapping("/{id}")
	public void deleteShift(@PathVariable Integer id) {
		shiftService.deleteShift(id);
	}
}
