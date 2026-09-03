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
	public Page<Shift> getShifts(@RequestParam(required = false, defaultValue = "1") int pageNo, 
								 @RequestParam(required = false, defaultValue = "5") int pageSize) {
		
		return shiftRepository.findAll(PageRequest.of(pageNo-1, pageSize));
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
    public ResponseEntity<ShiftResponseDTO> createEmployee(@Valid @RequestBody ShiftRequestDTO request){

        Shift shift = shiftMapper.toEntity(request);

        Shift savedShift = shiftRepository.save(shift);

        ShiftResponseDTO shiftDTO = shiftMapper.toResponseDTO(savedShift);

        return ResponseEntity.status(HttpStatus.CREATED).body(shiftDTO);
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