package com.example.demo.entity;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="HOTEL")
public class Hotel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="HOTEL_ID")
	private int hotel_id;
	
	@NotNull(message = "Hotel name should not be null")
	@Column(name="NAME")
	private String name;
	
	@NotNull(message = "Location should not be null")
	@Column(name = "LOCATION")
	private String location;
	
	@Column(name="DESCRIPTION",columnDefinition="TEXT")
	private String description;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="HOTELAMENITY",joinColumns=@JoinColumn(name="HOTEL_ID"),inverseJoinColumns=@JoinColumn(name="AMENITY_ID"))
	@JsonManagedReference
	private List<Amenity> amenities;
	
	@OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Room> rooms;
	
	// Default Constructor
	public Hotel() {
		
	}

	// Constructor with parameters
	public Hotel(int hotel_id, String name, String location, String description) {
		super();
		this.hotel_id = hotel_id;
		this.name = name;
		this.location = location;
		this.description = description;
	}

	// Getters and setters
	public int getHotel_id() {
		return hotel_id;
	}

	public void setHotel_id(int hotel_id) {
		this.hotel_id = hotel_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Amenity> getAmenities() {
		return amenities;
	}

	public void setAmenities(List<Amenity> amenities) {
		this.amenities = amenities;
	}

	// To String method
	@Override
	public String toString() {
		return "Hotel [hotel_id=" + hotel_id + ", name=" + name + ", location=" + location + ", text="
				+ description + "]";
	}

}