package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.dto.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ReservationNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleReservationNotFoundException(ReservationNotFoundException ex){
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(ex.getCode(),ex.getMessage()),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(RoomNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleRoomNotFoundException(RoomNotFoundException ex){
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(ex.getCode(),ex.getMessage()),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(RoomTypeNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleRoomTypeNotFoundException(RoomTypeNotFoundException ex){
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(ex.getCode(),ex.getMessage()),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AmenityNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleAmenityNotFoundException(AmenityNotFoundException ex){
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(ex.getCode(),ex.getMessage()),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ReviewNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleReviewNotFoundException(ReviewNotFoundException ex){
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(ex.getCode(),ex.getMessage()),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PaymentNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlePaymentNotFoundException(PaymentNotFoundException ex){
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(ex.getCode(),ex.getMessage()),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(HotelNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleHotelNotFoundException(HotelNotFoundException ex){
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(ex.getCode(),ex.getMessage()),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex){
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(ex.getCode(),ex.getMessage()),HttpStatus.NOT_FOUND);
	}
	
}
