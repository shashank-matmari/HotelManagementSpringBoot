package com.example.demo.dto;
 
public class HotelAmenityDTO {
	private int hotel_id;
    private int amenity_id;
 
    // Constructors, getters, setters
    public HotelAmenityDTO() {}
 
	public HotelAmenityDTO(int hotel_id, int amenity_id) {
		super();
		this.hotel_id = hotel_id;
		this.amenity_id = amenity_id;
	}
 
	public int getHotel_id() {
		return hotel_id;
	}
 
	public void setHotel_id(int hotel_id) {
		this.hotel_id = hotel_id;
	}
 
	public int getAmenity_id() {
		return amenity_id;
	}
 
	public void setAmenity_id(int amenity_id) {
		this.amenity_id = amenity_id;
	}
 
    
}