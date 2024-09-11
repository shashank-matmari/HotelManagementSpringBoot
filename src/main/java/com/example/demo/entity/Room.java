package com.example.demo.entity;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "ROOM")
public class Room {
	
	@Id
	@Column(name="ROOM_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int room_id;
	
	@NotNull(message = "Room number should not be null")
	@Column(name="ROOM_NUMBER")
//	@NotEmpty
	private int room_number;
	
	@NotNull(message = "Availability should not be null")
	@Column(name="IS_AVAILABLE", columnDefinition = "BOOLEAN")
	private boolean is_available;
	
	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	@JsonIgnoreProperties
	private List<Reservation> reservation;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ROOM_TYPE_ID")
//	@JsonBackReference
	RoomType roomType;
	
	@ManyToMany
	@JoinTable(name="ROOMAMENITY",joinColumns = @JoinColumn(name="ROOM_ID"), inverseJoinColumns = @JoinColumn(name="AMENITY_ID"))
	@JsonManagedReference
	@JsonIgnoreProperties
	private List<Amenity> amenities;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="HOTEL_ID")
//	@JsonBackReference
	private Hotel hotel;
	
	// Default Constructor
	public Room() {
		
	}

	// Constructor wit parameters
	public Room(int room_number, boolean is_available) {
		super();
		this.room_number = room_number;
		this.is_available = is_available;
	}

	// Getters and setters
	public int getRoom_id() {
		return room_id;
	}

	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}

	public int getRoom_number() {
		return room_number;
	}

	public void setRoom_number(int room_number) {
		this.room_number = room_number;
	}

	public boolean isIs_available() {
		return is_available;
	}

	public void setIs_available(boolean is_available) {
		this.is_available = is_available;
	}

	public List<Reservation> getReservation() {
		return reservation;
	}

	public void setReservation(List<Reservation> reservation) {
		this.reservation = reservation;
	}

	@JsonProperty("room_type")
	public RoomType getRoomType() {
		return roomType != null ? new RoomType(
			roomType.getRoom_type_id(),
			roomType.getType_name(),
			roomType.getDescription(),
			roomType.getMax_occupancy(),
			roomType.getPrice_per_night()
		) : null;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public List<Amenity> getAmenities() {
		return amenities;
	}

	public void setAmenities(List<Amenity> amenities) {
		this.amenities = amenities;
	}

	@JsonProperty("hotel")
	public Hotel getHotel() {
		return hotel != null ? new Hotel(hotel.getHotel_id(),hotel.getName(),hotel.getLocation(),hotel.getDescription()) : null;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
}
