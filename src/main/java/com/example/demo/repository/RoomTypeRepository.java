package com.example.demo.repository;

import com.example.demo.entity.RoomType;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Integer> {
	
	// Custom query to find room type by its name
	@Query("select r from RoomType r where r.type_name=:type_name")
	public List<RoomType> getRoomTypeByName(String type_name);
	
}
