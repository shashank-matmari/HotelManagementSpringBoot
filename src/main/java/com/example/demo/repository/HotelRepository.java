package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
	

	    // Custom query to find hotels by amenity ID
	    @Query("SELECT h FROM Hotel h JOIN h.amenities a WHERE a.amenity_id = :amenity_id")
	    List<Hotel> findByAmenityId(@Param("amenity_id") int amenity_id);
	    
	    // Custom query to find hotels by location
	    @Query("select h from Hotel h where h.location=:location")
		List<Hotel> getHotelsByLocation(@Param("location") String location);
	   
	}

	    
	   



