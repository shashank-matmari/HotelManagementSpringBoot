package com.example.demo.services;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.dto.DetailsDTO;
import com.example.demo.dto.ReservationDTO;
import com.example.demo.dto.SuccessResponse;
import com.example.demo.entity.Reservation;
import com.example.demo.exception.ReservationNotFoundException;
import com.example.demo.exception.UserNotFoundException;

public interface ReservationService {
	
	Reservation createReservation(Reservation reservation) throws ReservationNotFoundException;
	
	List<Reservation> getAllReservations() throws ReservationNotFoundException;
	
	Reservation getReservationById(int reservationId) throws ReservationNotFoundException;
	
	SuccessResponse deleteReservationById(int reservationId) throws ReservationNotFoundException;
	
	SuccessResponse updateReservationById(Reservation reservation) throws ReservationNotFoundException;
	
	List<ReservationDTO> getReservationInDateRange(LocalDate startDate,LocalDate endDate) throws ReservationNotFoundException;
	
	List<ReservationDTO> getAllReservationsWithRoom() throws ReservationNotFoundException;
	
	List<DetailsDTO> getAllDetails();
	
	ReservationDTO getReservationwithRoom(int reservationId) throws ReservationNotFoundException;
	
	List<DetailsDTO> getAllDetailsByReservationId(int reservationId) throws ReservationNotFoundException;
	
	List<Reservation> getAllReservationByUser(String email) throws UserNotFoundException;

}
