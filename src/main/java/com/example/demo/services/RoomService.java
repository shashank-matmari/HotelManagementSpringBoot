package com.example.demo.services;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.example.demo.dto.RoomDTO;
import com.example.demo.dto.SuccessResponse;
import com.example.demo.entity.Room;
import com.example.demo.exception.AmenityNotFoundException;
import com.example.demo.exception.LocationNotFoundException;
import com.example.demo.exception.RoomNotFoundException;
import com.example.demo.exception.RoomTypeNotFoundException;

public interface RoomService {
	
//	SuccessResponse createNewRoom(RoomDTO room) throws RoomTypeNotFoundException;
	
	List<Room> getAllRooms();
	
	Room getRoomById(int room_id) throws RoomNotFoundException;
	
	List<Room> getAvailableRoomsByType(int roomTypeId) throws RoomTypeNotFoundException;
	
	List<Room> getRoomsWithSpecificAmenityId(int amenityId) throws AmenityNotFoundException;
	
	SuccessResponse deleteRoomById(int room_id) throws RoomNotFoundException;
	
	
	SuccessResponse updateRoom(int room_id, RoomDTO room) throws RoomNotFoundException, RoomTypeNotFoundException;
	
	List<Room> getRoomsAvailableByDateRange(Integer hotelId,String startDate,String endDate) throws ParseException;
	
	SuccessResponse addAmenityToRoom(int roomId, int amenityId) throws RoomNotFoundException, AmenityNotFoundException;

	SuccessResponse createNewRoom(int hotel_id, RoomDTO room) throws RoomTypeNotFoundException;
}
