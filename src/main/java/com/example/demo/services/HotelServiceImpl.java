package com.example.demo.services;

import com.example.demo.entity.Amenity;
import com.example.demo.entity.Hotel;
import com.example.demo.entity.Room;
import com.example.demo.exception.AmenityNotFoundException;
import com.example.demo.exception.HotelNotFoundException;
import com.example.demo.exception.RoomNotFoundException;
import com.example.demo.dto.SuccessResponse;
import com.example.demo.repository.AmenityRepository;
import com.example.demo.repository.HotelRepository;
import com.example.demo.repository.RoomRepository;
import com.example.demo.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    HotelRepository hotelRepository;
    
    @Autowired
    RoomRepository roomRepository;
    
    @Autowired
    AmenityRepository amenityRepository;

    // Method to create hotel
    @Override
	public Hotel createHotel(Hotel hotel) throws HotelNotFoundException {
		// TODO Auto-generated method stub
		if(hotelRepository.findById(hotel.getHotel_id()).isEmpty()) {
			return hotelRepository.save(hotel);
		} else 
			throw new HotelNotFoundException("ADDFAILS", "Hotel already exist");
	}
 
    // Method to get all hotels
	@Override
	public List<Hotel> getAllHotels() throws HotelNotFoundException {
		// TODO Auto-generated method stub
		if(hotelRepository.findAll().isEmpty())
			throw new HotelNotFoundException("GETFAILS" , "Hotel list is empty");
		else 
			return hotelRepository.findAll();
	}

	// Method to find hotel by id
    @Override
    public Hotel getHotelById(int hotel_id) throws HotelNotFoundException{
    	if(hotelRepository.findById(hotel_id).isEmpty())
    		throw new HotelNotFoundException("GETFAILS", "Hotel doesn't exist exist");
        return hotelRepository.findById(hotel_id).get();
    }

    // Method to find hotels by amenity id
    @Override
    public List<Hotel> getHotelsByAmenityId(int amenity_id)throws AmenityNotFoundException, HotelNotFoundException {
    	if(amenityRepository.findById(amenity_id).isEmpty())
    		throw new AmenityNotFoundException("GETFAILS", "Amanity doesn't exist");
    	else if (hotelRepository.findByAmenityId(amenity_id).isEmpty())
    		throw new AmenityNotFoundException("GETFAILS", "No hotel is found with the spacific amenity");
        return hotelRepository.findByAmenityId(amenity_id);
    }

    // Method to update hotel details
    @Override
    public SuccessResponse updateHotel(Hotel hotel) throws HotelNotFoundException {
    	if(hotelRepository.findById(hotel.getHotel_id()).isEmpty())
    		throw new HotelNotFoundException("UPDTFAILS", "Hotel doesn't exist");
    	else {
    		hotelRepository.save(hotel);
    		return new SuccessResponse("UPDATESUCCESS", "Hotel updated successfully");
    	}
        
    }

    // Method to delete a hotel by id
    @Override
    public SuccessResponse deleteHotelById(int hotel_id)throws HotelNotFoundException {
    	if(hotelRepository.findById(hotel_id).isEmpty()) {
    		throw new HotelNotFoundException("GETFAILS", "Hotel doesn't exist");
    }else {	
    		
        hotelRepository.deleteById(hotel_id);
        return new SuccessResponse("DELETESUCCESS","Hotel deleted successfully");
    }
        
    }
    
    // Method to get all rooms in a hotel by hotel id
    @Override
	public List<Room> getAllRoomsInHotel(int hotelId) throws HotelNotFoundException, RoomNotFoundException{
		// TODO Auto-generated method stub
		if(hotelRepository.findById(hotelId)==null) {
			throw new HotelNotFoundException("GETFAILS","Hotel doesn't exist");
		}else {
			return roomRepository.getAllRoomsByHotelId(hotelId);
		}
    }
    
    // Method to get hotels by location
    @Override
	public List<Hotel> getHotelByLocation(String location) throws HotelNotFoundException {
		// TODO Auto-generated method stub
		if(hotelRepository.getHotelsByLocation(location).isEmpty())
			throw new HotelNotFoundException("GETFAILS", "Hotel list is empty");
		else
			return hotelRepository.getHotelsByLocation(location);
	}
    
    // Method to add amenity to a hotel
    @Override
	public SuccessResponse addAmenityToHotel(int hotelId, int amenityId) throws HotelNotFoundException, AmenityNotFoundException {
		Optional<Hotel> hotel=hotelRepository.findById(hotelId);
		
		Optional<Amenity> amenity=amenityRepository.findById(amenityId);
        
        if (hotel.isEmpty()) {
            throw new HotelNotFoundException("GETFAILS", "Hotel doesn't exist");
        } else if(amenity.isEmpty()) {
        	throw new AmenityNotFoundException("GETFAILS", "Amenity doesn't exist");
        }else {
        	Hotel currentHotel=hotel.get();
        	Amenity currentAmenity=amenity.get();
        	
        	List<Amenity> amenities=currentHotel.getAmenities();
        	amenities.add(currentAmenity);
        	currentHotel.setAmenities(amenities);
        	hotelRepository.save(currentHotel);
        	
        	List<Hotel> hotels=currentAmenity.getHotel();
			hotels.add(currentHotel);
			currentAmenity.setHotel(hotels);
			amenityRepository.save(currentAmenity);
	        return new SuccessResponse("POSTSUCCESS","Hotelamenity added successfully");
 
		}
	}
    
}

