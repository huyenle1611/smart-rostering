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

import com.rostering.mapper.ShiftMapper;
import com.rostering.model.dto.ShiftRequestDTO;
import com.rostering.model.dto.ShiftResponseDTO;
import com.rostering.model.entity.Shift;
import com.rostering.repository.IShiftRepository;

import jakarta.validation.Valid;

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
	
	@GetMapping
	public List<ShiftResponseDTO> getShifts() {
		List<Shift> shifts = shiftRepository.findAll();
        List<ShiftResponseDTO> shiftDTOs = new ArrayList<>();

        for(Shift shift : shifts){
            ShiftResponseDTO shiftDTO = shiftMapper.toResponseDTO(shift);
            shiftDTOs.add(shiftDTO);
        }
        return shiftDTOs;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ShiftResponseDTO> getShiftById(@PathVariable Integer id) {
        Optional<Shift> shiftWrapper = shiftRepository.findById(id);
        if (shiftWrapper.isPresent()) {
            Shift shiftObj = shiftWrapper.get();
            ShiftResponseDTO dto = shiftMapper.toResponseDTO(shiftObj);
            //200 success
            return ResponseEntity.ok(dto);
        } else {
            //404 not found
            return ResponseEntity.notFound().build();
        }
    }
	
	@PostMapping
    public ResponseEntity<ShiftResponseDTO> createEmployee(@Valid @RequestBody ShiftRequestDTO request){
        // Map requestDTO > entity
        Shift shift = shiftMapper.toEntity(request);
        // Save shift to database
        Shift savedShift = shiftRepository.save(shift);
        // Map savedShift to shiftResponseDTO
        ShiftResponseDTO shiftDTO = shiftMapper.toResponseDTO(savedShift);
        // Return http status + dto 
        return ResponseEntity.status(HttpStatus.CREATED).body(shiftDTO);
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShift(@PathVariable Integer id){
        Optional<Shift> shiftWrapper = shiftRepository.findById(id);
        if(shiftWrapper.isPresent()){
            Shift shift = shiftWrapper.get();
            shiftRepository.delete(shift);
            //return 204 no content > delete successfully
            return ResponseEntity.noContent().build();
        } else {
            //404 not found
            return ResponseEntity.notFound().build();
        }
    }
}
