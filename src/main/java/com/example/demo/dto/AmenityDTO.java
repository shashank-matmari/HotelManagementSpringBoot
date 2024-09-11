package com.example.demo.dto;

import jakarta.persistence.Column;

public class AmenityDTO {
	
	private String name;
	
	private String description;

	// Default Constructor
	public AmenityDTO() {
		
	}
	
	// Constructor with parameters
	public AmenityDTO(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	// Getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
