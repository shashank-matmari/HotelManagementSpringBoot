package com.example.demo.dto;

import java.util.List;

public class HotelDto {
	private String name;
	private String description;
	private String location;
	
	
	
	// Default Constructor
	public HotelDto() {
		super();
	}


	// Constructor with parameters
	public HotelDto(int hotel_id, String name, String description, String location) {
		super();
		this.name = name;
		this.description = description;
		this.location = location;
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



	public String getLocation() {
		return location;
	}



	public void setLocation(String location) {
		this.location = location;
	}






	// To String method
	@Override
	public String toString() {
		return "HotelDto [name=" + name + ", description=" + description + ", location="
				+ location + "]";
	}
	
	 	

	
	
	

}
