package com.rostering.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public Page<Shift> getShifts(@RequestParam(required = false, defaultValue = "0") int pageNo) {
		int pageSize = 30;
		return shiftRepository.findAll(PageRequest.of(pageNo, pageSize));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ShiftResponseDTO> getShiftById(@PathVariable Integer id) {
        Optional<Shift> shiftWrapper = shiftRepository.findById(id);
        if (shiftWrapper.isPresent()) {
            Shift shiftObj = shiftWrapper.get();
            ShiftResponseDTO dto = shiftMapper.toResponseDTO(shiftObj);

            return ResponseEntity.ok(dto);
        } else {

            return ResponseEntity.notFound().build();
        }
    }
	
	@PostMapping
    public ResponseEntity<String> createEmployee(@Valid @RequestBody ShiftRequestDTO request){

        Shift shift = shiftMapper.toEntity(request);

        shiftRepository.save(shift);

        return ResponseEntity.status(HttpStatus.CREATED).body("Shift created successfully");
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShift(@PathVariable Integer id){
        Optional<Shift> shiftWrapper = shiftRepository.findById(id);
        if(shiftWrapper.isPresent()){
            Shift shift = shiftWrapper.get();
            shiftRepository.delete(shift);

            return ResponseEntity.noContent().build();
        } else {

            return ResponseEntity.notFound().build();
        }
    }
}