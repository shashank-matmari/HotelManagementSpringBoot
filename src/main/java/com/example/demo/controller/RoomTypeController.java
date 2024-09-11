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

import com.example.demo.dto.RoomTypeDTO;
import com.example.demo.dto.SuccessResponse;
//import com.example.DTO.InitAccount;
import com.example.demo.entity.RoomType;
import com.example.demo.exception.RoomTypeNotFoundException;
//import com.example.demo.ExceptionHandling.CustomerNotFoundException;
//import com.example.demo.ExceptionHandling.InvalidBalanceException;
import com.example.demo.services.RoomTypeService;
import com.example.demo.services.RoomTypeServiceImpl;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/RoomType/")
@CrossOrigin("http://localhost:4200/")
public class RoomTypeController{
	@Autowired
	RoomTypeService roomTypeService;
	
	// API to get all roomtypes
	@GetMapping("all")
	public ResponseEntity<List<RoomType>> getAllRoomType() throws RoomTypeNotFoundException{
		return new ResponseEntity<List<RoomType>>(roomTypeService.getAllRoomType(),HttpStatus.OK);
	}
	
	// API to get roomtype by id
	@GetMapping("{RoomType_id}")
	public ResponseEntity<RoomType>getbyId(@PathVariable("RoomType_id") int RoomType_id) throws RoomTypeNotFoundException{
		return new ResponseEntity<RoomType>(roomTypeService.getById(RoomType_id),HttpStatus.OK) ;
	}
	
	// API to delete a roomtype
	@DeleteMapping("/delete/{RoomType_id}")
	public ResponseEntity<SuccessResponse>deletebyId(@PathVariable("RoomType_id") int RoomType_id) throws RoomTypeNotFoundException{
		return new ResponseEntity<SuccessResponse>(roomTypeService.deleteById(RoomType_id),HttpStatus.OK) ;
	}
	
	// API to create a new roomtype
	@PostMapping("/post")
	public ResponseEntity<SuccessResponse> createRoomType(@RequestBody RoomTypeDTO roomtype) throws RoomTypeNotFoundException{
		return new ResponseEntity<SuccessResponse>(roomTypeService.createRoomType(roomtype),HttpStatus.CREATED);
	}
	
	// API to update a roomtype
	@PutMapping("/update/{RoomType_id}")
	public ResponseEntity<SuccessResponse> updateRoomTypeById(@RequestBody RoomTypeDTO roomtype,@PathVariable("RoomType_id") int RoomType_id)throws RoomTypeNotFoundException{
		return new ResponseEntity<SuccessResponse>(roomTypeService.updateRoomTypeById(roomtype, RoomType_id),HttpStatus.OK);
	}
	
}

