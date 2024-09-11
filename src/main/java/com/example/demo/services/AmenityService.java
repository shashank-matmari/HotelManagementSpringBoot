package com.example.demo.services;

import java.util.List;

import com.example.demo.entity.Amenity;
import com.example.demo.exception.AmenityNotFoundException;
import com.example.demo.exception.HotelNotFoundException;
import com.example.demo.exception.RoomNotFoundException;
import com.example.demo.dto.SuccessResponse;

public interface AmenityService {
	
	//creating Amenity	
    SuccessResponse createAmenity(Amenity amenity);
    
    //Getting  All Amenities
	public List<Amenity> getAllAmenities()throws AmenityNotFoundException;
	
	//Getting Amenity By Id 
	public Amenity getAmenityById(int amenity_id) throws AmenityNotFoundException;
	
	//getting Amenity By Hotel Id 
	public List<Amenity> getAmenitByHotelId(int hotel_id) throws AmenityNotFoundException,HotelNotFoundException;
	
	//Getting Amenity details by Room Id 
	public List<Amenity> getAmenitByRoomId(int room_id)throws AmenityNotFoundException,RoomNotFoundException;
	
	//Deleting Amenity By Id
	SuccessResponse deleteAmenityById(int amenity_id) throws AmenityNotFoundException;
	
	// Updating Amenity By Id
	SuccessResponse updateAmenityById(Amenity amenity) throws AmenityNotFoundException;
	
}
