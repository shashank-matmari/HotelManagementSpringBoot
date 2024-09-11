package com.example.demo.entity;
 
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
 
@Entity
@Table(name="AMENITY")
public class Amenity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="AMENITY_ID")
	private int amenity_id;
	
	@NotNull(message = "Amenity name should not be null")
	@Column(name="NAME")
	private String name;

	@Size(min = 10, message = "Description must be atleast 10 characters")
	@Column(name="DESCRIPTION",columnDefinition = "TEXT")
	private String description;
	
	// Default Constructor
	public Amenity() {
		
	}

	// Constructor with parameters
	public Amenity(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	@ManyToMany(mappedBy = "amenities")
	@JsonBackReference
	private List<Hotel> hotel;
	
	@ManyToMany(mappedBy="amenities")
	@JsonBackReference
	private List<Room> room;

	// Getters and setters
	public int getAmenity_id() {
		return amenity_id;
	}

	public void setAmenity_id(int amenity_id) {
		this.amenity_id = amenity_id;
	}

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

	public List<Hotel> getHotel() {
		return hotel;
	}

	public void setHotel(List<Hotel> hotel) {
		this.hotel = hotel;
	}

	public List<Room> getRoom() {
		return room;
	}

	public void setRoom(List<Room> room) {
		this.room = room;
	}
	
	

 
}