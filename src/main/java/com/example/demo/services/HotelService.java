package com.example.demo.services;

import com.example.demo.dto.SuccessResponse;
import com.example.demo.entity.Hotel;
import com.example.demo.entity.Room;
import com.example.demo.exception.AmenityNotFoundException;
import com.example.demo.exception.HotelNotFoundException;
import com.example.demo.exception.RoomNotFoundException;

import java.util.List;

public interface HotelService {
    Hotel createHotel(Hotel hotel) throws HotelNotFoundException;
    
    List<Hotel> getAllHotels() throws HotelNotFoundException;
    
    Hotel getHotelById(int hotel_id)throws HotelNotFoundException;
    
    List<Hotel> getHotelsByAmenityId(int amenity_id)throws AmenityNotFoundException, HotelNotFoundException;
    
    SuccessResponse updateHotel(Hotel hotel) throws HotelNotFoundException;
    
    SuccessResponse deleteHotelById(int hotel_id) throws HotelNotFoundException;
    
    List<Room> getAllRoomsInHotel(int hotelId) throws HotelNotFoundException, RoomNotFoundException;
    
    List<Hotel> getHotelByLocation(String location) throws HotelNotFoundException;
    
    SuccessResponse addAmenityToHotel(int hotelId, int amenityId) throws HotelNotFoundException, AmenityNotFoundException;
    
}
