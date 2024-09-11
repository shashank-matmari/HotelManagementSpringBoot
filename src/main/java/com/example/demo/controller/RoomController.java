package com.example.demo.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.example.demo.dto.*;
import com.example.demo.entity.Room;
import com.example.demo.exception.*;
import com.example.demo.services.RoomService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/rooms/")
@CrossOrigin("http://localhost:4200/")
public class RoomController {
	@Autowired
	RoomService RoomService;
	
	// API to create a new room
	@PostMapping("post/{hotel_id}")
	public ResponseEntity<SuccessResponse> createNewRoom(@PathVariable("hotel_id") int hotel_id,@RequestBody RoomDTO room) throws RoomTypeNotFoundException{
		return new ResponseEntity<SuccessResponse>(RoomService.createNewRoom(hotel_id,room),HttpStatus.CREATED);
	}
	
	// API to get all rooms 
	@GetMapping("all")
	public ResponseEntity<List<Room>> getAllRooms(){
		return new ResponseEntity<List<Room>>(RoomService.getAllRooms(),HttpStatus.OK);
	}
	
	// API to fetch room details by room id
	@GetMapping("{room_id}")
	public ResponseEntity<Room> getRoomById(@PathVariable("room_id") int room_id) throws RoomNotFoundException{
		return new ResponseEntity<Room>(RoomService.getRoomById(room_id),HttpStatus.OK);
	}
	
	// API to get available rooms by roomtype id
	@GetMapping("available/{roomTypeId}")
	public ResponseEntity<List<Room>> getAvailableRoomsByType(@PathVariable("roomTypeId") int roomTypeId) throws RoomTypeNotFoundException{
		return new ResponseEntity<List<Room>>(RoomService.getAvailableRoomsByType(roomTypeId),HttpStatus.OK);
	}
	
//	@GetMapping("location/{location}")
//	public ResponseEntity<Room> getRoomsByLocation(@PathVariable("location") String location){
//		return new ResponseEntity<Room>(HttpStatus.OK);
//	}
	
	// API to get room details having specific amenity by amenity id
	@GetMapping("amenities/{amenityId}")
	public ResponseEntity<List<Room>> getRoomsWithSpecificAmenityId(@PathVariable("amenityId") int amenityId) throws AmenityNotFoundException{
		return new ResponseEntity<List<Room>>(RoomService.getRoomsWithSpecificAmenityId(amenityId), HttpStatus.OK);
	}
	
	// API to update a room 
	@PutMapping("update/{room_id}")
	public ResponseEntity<SuccessResponse> updateRoom(@PathVariable("room_id") int room_id,@RequestBody RoomDTO Room) throws RoomNotFoundException, RoomTypeNotFoundException{
		return new ResponseEntity<SuccessResponse>(RoomService.updateRoom(room_id, Room),HttpStatus.OK);
	}
	
	// API to delete a room 
	@DeleteMapping("{room_id}")
	public ResponseEntity<SuccessResponse> deleteRoomById(@PathVariable("room_id") int room_id) throws RoomNotFoundException{
		return new ResponseEntity<SuccessResponse>(RoomService.deleteRoomById(room_id),HttpStatus.NO_CONTENT);
	}
	
	// API to get available rooms in a hotel
	@GetMapping("/getAvalilability/{hotelId}/")
	public ResponseEntity<List<Room>> getRoomsAvailableByDateRange(@PathVariable("hotelId") Integer hotelId,@RequestParam("startDate")String startDate, @RequestParam("endDate") String endDate) throws ParseException{
		return new ResponseEntity<List<Room>>(RoomService.getRoomsAvailableByDateRange(hotelId,startDate,endDate),HttpStatus.OK);
	}
	
	// API to add amenity to a room
	@PostMapping("/roomAmenity/post")
	public ResponseEntity<SuccessResponse> addAmenityToRoom(@RequestParam("room_id") int room_id,@RequestParam("amenity_id") int amenity_id) throws RoomNotFoundException, AmenityNotFoundException{
		return new ResponseEntity<SuccessResponse>(RoomService.addAmenityToRoom(room_id, amenity_id), HttpStatus.CREATED);
	}
	
}
