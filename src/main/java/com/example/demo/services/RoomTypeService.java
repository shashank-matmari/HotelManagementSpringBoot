package com.example.demo.services;

import com.example.demo.dto.RoomTypeDTO;
import com.example.demo.dto.SuccessResponse;
import com.example.demo.entity.RoomType;
import com.example.demo.exception.RoomTypeNotFoundException;

import java.util.List;
import java.util.Optional;

public interface RoomTypeService {
	
	List<RoomType> getAllRoomType() throws RoomTypeNotFoundException;
	
    RoomType getById(int id) throws RoomTypeNotFoundException;
    
    SuccessResponse deleteById(int id) throws RoomTypeNotFoundException;
    
    SuccessResponse createRoomType(RoomTypeDTO roomtype) throws RoomTypeNotFoundException;
    
	SuccessResponse updateRoomTypeById(RoomTypeDTO roomtype,int id) throws RoomTypeNotFoundException;

}
