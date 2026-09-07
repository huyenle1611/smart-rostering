package com.rostering.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rostering.mapper.ShiftMapper;
import com.rostering.repository.IShiftRepository;

@RestController
@RequestMapping("/api/v1/shifts")
public class ShiftController {
	private final IShiftRepository shiftRepository;
    private final ShiftMapper shiftMapper;
	
    public ShiftController(IShiftRepository shiftRepository,
            ShiftMapper shiftMapper) {
        this.shiftRepository = shiftRepository;
    	this.shiftMapper = shiftMapper;
        
    }

}