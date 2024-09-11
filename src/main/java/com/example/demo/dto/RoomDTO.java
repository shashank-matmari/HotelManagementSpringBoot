package com.example.demo.dto;
 
public class RoomDTO {
	private int room_number;
	private boolean is_available;
	private int room_type_id;

	// Default Constructor
	public RoomDTO() {
	}
 
	// Getters and setters
	public int getRoom_number() {
		return room_number;
	}
 
 
	public void setRoom_number(int room_number) {
		this.room_number = room_number;
	}
 
 
	public boolean getIs_available() {
		return is_available;
	}
 
 
	public void setIs_available(boolean is_available) {
		this.is_available = is_available;
	}
 
 
	public int getRoom_type_id() {
		return room_type_id;
	}
 
 
	public void setRoom_type_id(int room_type_id) {
		this.room_type_id = room_type_id;
	}
 
	// Constructor with parameters
	public RoomDTO(int room_number, boolean is_available, int room_type_id) {
		super();
		this.room_number = room_number;
		this.is_available = is_available;
		this.room_type_id = room_type_id;
	}
 
	
 
}