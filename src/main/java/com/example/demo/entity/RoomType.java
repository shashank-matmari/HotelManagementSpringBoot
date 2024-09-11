package com.example.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="ROOMTYPE")
public class RoomType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ROOM_TYPE_ID")
	private int room_type_id;
	
	@NotNull(message = "Room type name should not be null")
	@Column(name="TYPE_NAME")
	private String type_name;
	
	@Size(min = 10, message = "Description should be minimum 10 characters")
	@Column(name="DESCRIPTION",columnDefinition = "TEXT")
	private String description;
	
	@NotNull(message = "Occupancy should not be null")
	@Min(value = 1, message = "Occupancy should be greater than 0")
	@Column(name="MAX_OCCUPANCY")
	private int max_occupancy;
	
	@NotNull(message = "Price should not be null")
	@Column(name="PRICE_PER_NIGHT",columnDefinition = "DECIMAL(10,2)")
	private double price_per_night;
	
	@OneToMany(mappedBy = "roomType", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	@JsonIgnore
	private List<Room> room;

	// Default Constructor
	public RoomType() {
		super();
	}

	// Constructor with parameters
	public RoomType(String typeName, String description, int maxOccupancy, double pricePerNight) {
		super();
		this.type_name = typeName;
		this.description = description;
		this.max_occupancy = maxOccupancy;
		this.price_per_night = pricePerNight;
	}

	// Constructor with parameters
	public RoomType(int room_type_id, String type_name, String description, int max_occupancy, double price_per_night) {
		super();
		this.room_type_id = room_type_id;
		this.type_name = type_name;
		this.description = description;
		this.max_occupancy = max_occupancy;
		this.price_per_night = price_per_night;
	}

	// Getters and Setters
	public int getRoom_type_id() {
		return room_type_id;
	}

	public void setRoom_type_id(int room_type_id) {
		this.room_type_id = room_type_id;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMax_occupancy() {
		return max_occupancy;
	}

	public void setMax_occupancy(int max_occupancy) {
		this.max_occupancy = max_occupancy;
	}

	public double getPrice_per_night() {
		return price_per_night;
	}

	public void setPrice_per_night(double price_per_night) {
		this.price_per_night = price_per_night;
	}

	public List<Room> getRoom() {
		return room;
	}

	public void setRoom(List<Room> room) {
		this.room = room;
	}

	// To String method
	@Override
	public String toString() {
		return "RoomType [roomTypeId=" + room_type_id + ", typeName=" + type_name + ", description=" + description
				+ ", maxOccupancy=" + max_occupancy + ", pricePerNight=" + price_per_night + "]";
	}
	
	

}
