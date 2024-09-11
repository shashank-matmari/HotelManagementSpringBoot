package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Amenity;
import com.example.demo.exception.AmenityNotFoundException;
import com.example.demo.exception.HotelNotFoundException;
import com.example.demo.exception.RoomNotFoundException;
import com.example.demo.services.AmenityService;
import com.example.demo.dto.AmenityDTO;
import com.example.demo.dto.SuccessResponse;


@RestController
@RequestMapping("api/amenity/")
@CrossOrigin("http://localhost:4200/")
public class AmenityController {
	
	@Autowired
	AmenityService amenityService;
	
	//creating path-{"api/amenity/"}
	@PostMapping("post")
	public ResponseEntity<SuccessResponse> creatAmenity(@RequestBody AmenityDTO amenityDto) throws AmenityNotFoundException{
		Amenity amenity=new Amenity(amenityDto.getName(),amenityDto.getDescription());
		return new ResponseEntity<SuccessResponse>(amenityService.createAmenity(amenity),HttpStatus.CREATED);
	}
	
    //Getting  All Amenities Path-{"api/amenity/all"} 
	@GetMapping("all")
	public ResponseEntity<List<Amenity>> getAllAmenities() throws AmenityNotFoundException{
		return new ResponseEntity<List<Amenity>>(amenityService.getAllAmenities(),HttpStatus.OK);
	}
	
	//Getting Amenity By Id path-{"api/amenity/{amenity_id}"}
	@GetMapping("{amenity_id}")
	public ResponseEntity<Amenity> getAmenityById(@PathVariable("amenity_id") int amenity_id) throws AmenityNotFoundException{
		return new ResponseEntity<Amenity>(amenityService.getAmenityById(amenity_id),HttpStatus.OK);
	}
	
	
	//Getting Amenity By Hotel Id Path-{"api/amenity/hotel/{hotel_id}"}
	@GetMapping("hotel/{hotel_id}")
	public ResponseEntity<List<Amenity>> getAmenitByHotelId(@PathVariable("hotel_id") int hotel_id) throws AmenityNotFoundException, HotelNotFoundException{
		return new ResponseEntity<List<Amenity>>(amenityService.getAmenitByHotelId(hotel_id),HttpStatus.OK);
	}
	
	
	//Getting all amenities provided by a room with respect to its room id path-{"api/amenity/room{room_id}"}
	@GetMapping("room/{room_id}")
	public ResponseEntity<List<Amenity>> getAmenitByRoomId(@PathVariable("room_id") int room_id) throws AmenityNotFoundException, RoomNotFoundException{
		return new ResponseEntity<List<Amenity>>(amenityService.getAmenitByRoomId(room_id),HttpStatus.OK);
	}
	
	
	//Deleting Amenity By Id Path-{"api/amenity/delete/{amenity_id}"}
	@DeleteMapping("delete/{amenity_id}")
	public ResponseEntity<SuccessResponse> deleteAmenityById(@PathVariable("amenity_id") int amenity_id) throws AmenityNotFoundException{
		return new ResponseEntity<SuccessResponse>(amenityService.deleteAmenityById(amenity_id),HttpStatus.OK);
		
	}
	
	//Updating Amenity By Id path-{"api/amenity/update/{amenity_id}"}
	@PutMapping("update/{amenity_id}")
	public ResponseEntity<SuccessResponse> updateAmenityById(@RequestBody AmenityDTO amenityDto,@PathVariable("amenity_id") int amenity_id)throws AmenityNotFoundException{
		Amenity amenit=amenityService.getAmenityById(amenity_id);
		amenit.setName(amenityDto.getName());
		amenit.setDescription(amenityDto.getDescription());
		return new ResponseEntity<SuccessResponse>(amenityService.updateAmenityById(amenit),HttpStatus.OK);
		
		
	}
	
	

}
