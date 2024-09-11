package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Amenity;

@Repository
public interface AmenityRepository extends JpaRepository<Amenity,Integer>{
	
	// Custom query to find amenities by hotel id
	@Query("select a from Amenity a join a.hotel h where h.hotel_id=:hotel_id")
	List<Amenity> getAmenitByHotelId(int hotel_id);
	
	//Custom query to find amenities by room id
	@Query("select a from Amenity a join a.room r where r.room_id=:room_id")
	List<Amenity> getAmenitByRoomId(int room_id);


	
	


}
