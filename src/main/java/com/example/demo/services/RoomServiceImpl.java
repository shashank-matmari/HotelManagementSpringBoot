package com.example.demo.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RoomDTO;
import com.example.demo.dto.SuccessResponse;
import com.example.demo.entity.Amenity;
import com.example.demo.entity.Hotel;
import com.example.demo.entity.Room;
import com.example.demo.entity.RoomType;
import com.example.demo.exception.AmenityNotFoundException;
import com.example.demo.exception.LocationNotFoundException;
import com.example.demo.exception.RoomNotFoundException;
import com.example.demo.exception.RoomTypeNotFoundException;
import com.example.demo.repository.AmenityRepository;
import com.example.demo.repository.HotelRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.RoomTypeRepository;

@Service
public class RoomServiceImpl implements RoomService{
	@Autowired
	RoomRepository RoomRepo;
	
	@Autowired
	AmenityRepository AmenityRepo;
	
	@Autowired
	RoomTypeRepository RoomTypeRepo;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	HotelRepository hotelRepository;
	
	// Method to create room
	@Override
	public SuccessResponse createNewRoom(int hotel_id,RoomDTO room) throws RoomTypeNotFoundException{
		Optional<RoomType> roomType=RoomTypeRepo.findById(room.getRoom_type_id());
		Optional<Hotel> hotel=hotelRepository.findById(hotel_id);
		if(roomType.isEmpty()) {
			throw new RoomTypeNotFoundException("ADDFAILS","Roomtype doesnot exist");
		}else {
			Room newRoom=new Room(room.getRoom_number(), room.getIs_available());
			newRoom.setRoomType(roomType.get());
			newRoom.setHotel(hotel.get());
			RoomRepo.save(newRoom);
			return new SuccessResponse("POSTSUCCESS","Room added successfully");
		}
	}
	
	// Method to get all rooms
	@Override
	public List<Room> getAllRooms(){
		return RoomRepo.findAll();
	}

	// Method to get room by id
	@Override
	public Room getRoomById(int room_id) throws RoomNotFoundException {
		if(RoomRepo.findById(room_id).isEmpty()) {
			throw new RoomNotFoundException("GETALLFAILS","Room doesn't exist");
		}
		else {
			Room room=RoomRepo.findById(room_id).get();
			return room;
		}
	}
	
	// Method to get available rooms by room type
	@Override
	public List<Room> getAvailableRoomsByType(int roomTypeId) throws RoomTypeNotFoundException {
		if(RoomTypeRepo.findById(roomTypeId).isEmpty()) {
			throw new RoomTypeNotFoundException("GETFAILS","RoomType doesn't exist");
		}else {
			return RoomRepo.getAvailableRoomsByType(roomTypeId);
		}
	}

	// Method to get rooms with specific amenity id
	@Override
	public List<Room> getRoomsWithSpecificAmenityId(int amenityId) throws AmenityNotFoundException {
		if(AmenityRepo.findById(amenityId).isEmpty()) {
			throw new AmenityNotFoundException("GETALLFAILS","Amenity does't exist with given id");
		}else {
			return RoomRepo.getRoomsWithSpecificAmenityId(amenityId);
		}
	}

	// Method to update a room by id
	@Override
	public SuccessResponse updateRoom(int room_id, RoomDTO room) throws RoomNotFoundException, RoomTypeNotFoundException {
		Optional<Room> roomCopy= RoomRepo.findById(room_id);
		Optional<RoomType> roomType=RoomTypeRepo.findById(room.getRoom_type_id());
		if(roomCopy.isEmpty()) {
			throw new RoomNotFoundException("UPDTFAILS","Room doesn't exist");
		}
		else if(roomType.isEmpty()) {
			throw new RoomTypeNotFoundException("GETFAILS","RoomType doesn't exist ");
		}
		else {
			Room newRoom = roomCopy.get();
			newRoom.setRoom_number(room.getRoom_number());
			newRoom.setIs_available(room.getIs_available());
			newRoom.setRoomType(roomType.get());
			RoomRepo.save(newRoom);
			return (new SuccessResponse("UPDATESUCCESS","Room updated successfully"));
		}
	}
	
	// Method to delete room by id
	@Override
	public SuccessResponse deleteRoomById(int room_id) throws RoomNotFoundException {
//		if(RoomRepo.findById(room_id).isEmpty()) {
			throw new RoomNotFoundException("DLTFAILS","Task doesn't exist");
//		}
//		else {
//			Room cust=RoomRepo.findById(room_id).get();
//			RoomRepo.deleteById(room_id);
//			return (new SuccessResponse("DELETESUCCESS","task deleted successfully"));
//		}
	}
	
	// Method to get available rooms in a given date range
	@Override
	public List<Room> getRoomsAvailableByDateRange(Integer hotelId,String startDate,String endDate) throws ParseException{
		return RoomRepo.getAvailableRoomsInHotelByDateRange(hotelId, LocalDate.parse(startDate), LocalDate.parse(endDate));
//		List<Room> allRooms=RoomRepo.getAllRoomsByHotelId(hotelId);
//		List<Integer> bookedRoomsByDateRange=reservationRepository.getRoomIdBookedByDateRange(new SimpleDateFormat("yyyy-MM-dd").parse(startDate), new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
//		System.out.println(bookedRoomsByDateRange);
//		return allRooms.stream().filter((room)-> !bookedRoomsByDateRange.contains(room.getRoom_id())).collect(Collectors.toList());
	}
	
	// Method to add amenity to room
	@Override
    public SuccessResponse addAmenityToRoom(int roomId, int amenityId) throws RoomNotFoundException, AmenityNotFoundException{
		Optional<Room> room=RoomRepo.findById(roomId);
		
		Optional<Amenity> amenity=AmenityRepo.findById(amenityId);
		if(room.isEmpty()) {
			throw new RoomNotFoundException("GETFAILS", "Room doesn't exist");
		}else if(amenity.isEmpty()) {
        	throw new AmenityNotFoundException("GETFAILS", "Amenity doesn't exist");
		}else {
			Room currentRoom=room.get();
			Amenity currentAmenity=amenity.get();
			
			List<Amenity>amenities=currentRoom.getAmenities();
			amenities.add(currentAmenity);
			currentRoom.setAmenities(amenities);
			RoomRepo.save(currentRoom);
			
			List<Room> rooms=currentAmenity.getRoom();
			rooms.add(currentRoom);
			currentAmenity.setRoom(rooms);
			AmenityRepo.save(currentAmenity);
			return new SuccessResponse("POSTSUCCESS","Roomamenity added successfully");
					
		}
		
	}
	
}
