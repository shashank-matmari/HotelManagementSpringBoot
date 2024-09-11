package com.example.demo.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer>{

	// Custom query to find the available rooms by room type id
	@Query("select r from Room r where r.is_available=true and r.roomType.room_type_id=:roomTypeId")
	public List<Room> getAvailableRoomsByType(int roomTypeId);

	// Custom query to find the rooms with specific amenity id
	@Query("select r from Room r join amenities a where a.amenity_id=:amenityId")
	List<Room> getRoomsWithSpecificAmenityId(int amenityId);
	
	// Custom query to find all the room present in a specific hotel
	@Query("select r from Room r where r.hotel.hotel_id=:hotel_id")
	List<Room> getAllRoomsByHotelId(@Param("hotel_id")int hotel_id);

	// Custom query to find the available rooms in a hotel in given date range
	@Query("SELECT r FROM Room r WHERE r.hotel.hotel_id = :hotel_id AND r.room_id NOT IN (SELECT res.room.room_id FROM Reservation res WHERE res.check_in_date <= :endDate AND res.check_out_date >= :startDate)")
	List<Room> getAvailableRoomsInHotelByDateRange(@Param("hotel_id") int hotel_id,@Param("startDate") LocalDate startDate,@Param("endDate") LocalDate endDate);
	
}