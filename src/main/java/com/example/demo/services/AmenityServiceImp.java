package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Amenity;
import com.example.demo.exception.AmenityNotFoundException;
import com.example.demo.exception.HotelNotFoundException;
import com.example.demo.exception.RoomNotFoundException;
import com.example.demo.repository.AmenityRepository;
import com.example.demo.repository.RoomRepository;
import com.example.demo.dto.SuccessResponse;

@Service
public class AmenityServiceImp implements AmenityService {

	@Autowired
	AmenityRepository amenityrepository;
	
	@Autowired
	RoomRepository roomrepository;
	
	//Creating Amenity
	@Override
	public SuccessResponse createAmenity(Amenity amenity) {
		amenityrepository.save(amenity);
		return new SuccessResponse("POSTSUCCESS","Amenity added successfully");
	}
	
	//Getting  All Amenities
	@Override
	public List<Amenity> getAllAmenities() throws AmenityNotFoundException{
		if(amenityrepository.findAll().isEmpty()) {
			throw new AmenityNotFoundException("GETALLFAILS","Amentiy list is empty");
		}
		else
			return amenityrepository.findAll();
	}

	//Getting Amenity By Id 
	@Override
	public Amenity getAmenityById(int amenity_id) throws AmenityNotFoundException {
		if(amenityrepository.findById(amenity_id).isEmpty()) {
//			throw new AmenityNotFoundException("GETALLFAILS","Amenity doesn't exist");
			throw new AmenityNotFoundException("GETFAILS", "Amenity doesn't exist");
		}
		else
			return amenityrepository.findById(amenity_id).get();
	}
	

	//getting Amenity By Hotel Id 
	@Override
	public List<Amenity> getAmenitByHotelId(int hotel_id) throws AmenityNotFoundException,HotelNotFoundException{
		List<Amenity> amenities = amenityrepository.getAmenitByHotelId(hotel_id);
        if (amenities.isEmpty()) {
            throw new AmenityNotFoundException("GETALLFAILS","room not found with given hotel id"+hotel_id);
        }
        else
        	return amenityrepository.getAmenitByHotelId(hotel_id);
	}

	//Getting Amenity details by Room Id 
	@Override
	public List<Amenity> getAmenitByRoomId(int room_id) throws AmenityNotFoundException,RoomNotFoundException {
	        List<Amenity> amenities = amenityrepository.getAmenitByRoomId(room_id);
	        if (amenities.isEmpty()) {
	            throw new AmenityNotFoundException("GETALLFAILS","room not found with given room id"+room_id);
	        }
	        else
	        	return amenityrepository.getAmenitByRoomId(room_id);

	}



	//Deleting Amenity By Id
	@Override
	public SuccessResponse deleteAmenityById(int amenity_id) throws AmenityNotFoundException {
		if(amenityrepository.findById(amenity_id).isEmpty()) {
			throw new AmenityNotFoundException("DLTFAILS","Amenity doesn't exist exist");
		}
		else {
			amenityrepository.deleteById(amenity_id);
			return new SuccessResponse("DELETESUCCESS","Amenity delete successfully");
		}
		
		
	}
	
	
	// Updating Amenity By Id
	@Override
	public SuccessResponse updateAmenityById(Amenity amenity) throws AmenityNotFoundException {
		if(amenityrepository.findById(amenity.getAmenity_id()).isEmpty()) {
			throw new AmenityNotFoundException("UPDTFAILS","Amenity doesn't exist ");
		}
		else {
			amenityrepository.save(amenity);
			return new SuccessResponse("UPDATESUCCESS","Amenity updated successfully");
			
		}
	
	}
	
	

	
	
	
	
	

}
