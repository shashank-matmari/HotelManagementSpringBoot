package com.example.demo.controller;

import com.example.demo.dto.HotelAmenityDTO;
import com.example.demo.dto.HotelDto;
import com.example.demo.dto.SuccessResponse;
import com.example.demo.repository.AmenityRepository;
import com.example.demo.entity.Amenity;
import com.example.demo.entity.Hotel;
import com.example.demo.entity.Room;
import com.example.demo.exception.AmenityNotFoundException;
import com.example.demo.exception.HotelNotFoundException;
import com.example.demo.exception.RoomNotFoundException;
import com.example.demo.services.HotelService;

import org.apache.coyote.http11.Http11OutputBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@CrossOrigin("http://localhost:4200/")
public class HotelController {

    @Autowired
    private HotelService hotelService;
    
    @Autowired
    private AmenityRepository amenityRepository;

//  Insert new hotel
    @PostMapping("/post")
    public ResponseEntity<Hotel> createHotel(@RequestBody HotelDto hotel)throws HotelNotFoundException {
    	Hotel createHotel = new Hotel();
		createHotel.setName(hotel.getName());
		createHotel.setLocation(hotel.getLocation());
		createHotel.setDescription(hotel.getDescription());
		return new ResponseEntity<Hotel>(hotelService.createHotel(createHotel),HttpStatus.CREATED);
    }
    
//  API to fetch all hotels
    @GetMapping("/all")
    public ResponseEntity<List<Hotel>> getAllHotels()throws HotelNotFoundException {
        return new ResponseEntity<List<Hotel>>(hotelService.getAllHotels(),HttpStatus.OK);
    }

//  API to get details of a particular hotel by ite's ID
    @GetMapping("/{hotel_id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable("hotel_id") int hotel_id) throws HotelNotFoundException{
        Hotel hotel = hotelService.getHotelById(hotel_id);
        if (hotel != null) {
            return ResponseEntity.ok(hotel);
        }
        return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);
    }

//  Get List of hotel which provide specific amenity
    @GetMapping("/amenity/{amenity_id}")
    public ResponseEntity<List<Hotel>> getHotelsByAmenityId(@PathVariable("amenity_id") int amenity_id)throws AmenityNotFoundException,HotelNotFoundException {
        List<Hotel> hotels = hotelService.getHotelsByAmenityId(amenity_id);
        return new ResponseEntity<List<Hotel>>(hotelService.getHotelsByAmenityId(amenity_id),HttpStatus.OK);
    }

//  Update hotel details.
    @PutMapping("/update/{hotel_id}")
    public ResponseEntity<SuccessResponse> updateHotel(@PathVariable("hotel_id") int hotel_id, @RequestBody HotelDto hotel)throws HotelNotFoundException {
        Hotel updateHotel = hotelService.getHotelById(hotel_id);
        updateHotel.setName(hotel.getName());
		updateHotel.setLocation(hotel.getLocation());
		updateHotel.setDescription(hotel.getDescription());
		return new ResponseEntity<SuccessResponse>(hotelService.updateHotel(updateHotel), HttpStatus.OK);
	}	

//  Delete hotel
    @DeleteMapping("/delete/{hotel_id}")
    public ResponseEntity<SuccessResponse> deleteHotelById(@PathVariable("hotel_id") int hotel_id)throws HotelNotFoundException {
        throw new HotelNotFoundException("DLTFAILS", "You don't have permission to delete hotel");
    }
    
    @GetMapping("/location/{locationName}")
	public ResponseEntity<List<Hotel>> getHotelsByLocation(@PathVariable("locationName")String locationName) throws HotelNotFoundException{
		return new ResponseEntity<List<Hotel>>(hotelService.getHotelByLocation(locationName), HttpStatus.OK);
	}
    
//  fetch all the rooms present in the particular hotel by its hotel_id
    @GetMapping("/allrooms/{hotel_id}")
	public ResponseEntity<List<Room>> getAllRoomsInHotle(@PathVariable("hotel_id")int hotel_id) throws HotelNotFoundException, RoomNotFoundException{
		return new ResponseEntity<List<Room>>(hotelService.getAllRoomsInHotel(hotel_id),HttpStatus.OK);
	}
    
    // Endpoint to set amenity to a hotel
    @PostMapping("/hotelamenity/post")
    public ResponseEntity<SuccessResponse> addAmenityToHotel(@RequestBody HotelAmenityDTO hotelAmenity ) throws HotelNotFoundException, AmenityNotFoundException {
        return new ResponseEntity<SuccessResponse>(hotelService.addAmenityToHotel(hotelAmenity.getHotel_id(), hotelAmenity.getAmenity_id()),HttpStatus.CREATED);
    }
    
}

