package com.rostering.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Shift")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shift {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 255)
	private String name;
	
	@Column(nullable = false)
	private String date;
	
	@Column(nullable = false)
	private String startTime;
	
	@Column(nullable = false)
	private String endTime;
	
}	