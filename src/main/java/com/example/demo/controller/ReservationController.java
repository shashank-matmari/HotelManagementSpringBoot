package com.example.demo.controller;
 
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
import com.example.demo.dto.DetailsDTO;
import com.example.demo.dto.ReservationDTO;
import com.example.demo.dto.SuccessResponse;
import com.example.demo.entity.Reservation;
import com.example.demo.entity.Room;
import com.example.demo.exception.HotelNotFoundException;
import com.example.demo.exception.ReservationNotFoundException;
import com.example.demo.exception.RoomNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.services.ReservationService;
import com.example.demo.services.RoomService;

import jakarta.validation.Valid;
 
@RestController
@RequestMapping("/api/reservation")
@CrossOrigin(origins="http://localhost:4200")
public class ReservationController {
	
	@Autowired
	ReservationService reservationService;
	
	@Autowired
	RoomService roomService;
	
	// API to create a new reservation
	@PostMapping("/post")
	public ResponseEntity<Reservation> createNewReservation(@Valid @RequestBody ReservationDTO reservation) throws ReservationNotFoundException, RoomNotFoundException, HotelNotFoundException{
		Reservation createReservation = new Reservation();
		Room room = roomService.getRoomById(reservation.getRoom_id());
		createReservation.setRoom(room);
		createReservation.setGuest_name(reservation.getGuest_name());
		createReservation.setGuest_email(reservation.getGuest_email());
		createReservation.setGuest_phone(reservation.getGuest_phone());
		createReservation.setCheck_in_date(reservation.getCheck_in_date());
		createReservation.setCheck_out_date(reservation.getCheck_out_date());
		System.out.println(reservation.getRoom_id()+" "+reservation.getGuest_name()+" "+reservation.getGuest_email());
		System.out.println(reservation.getGuest_phone()+" "+reservation.getCheck_in_date()+" "+reservation.getCheck_out_date());
		return new ResponseEntity<Reservation>(reservationService.createReservation(createReservation),HttpStatus.OK);
	}
	
	// API to get all reservations
	@GetMapping("/all")
	public ResponseEntity<List<Reservation>> getAllReservations() throws ReservationNotFoundException{
		return new ResponseEntity<List<Reservation>>(reservationService.getAllReservations(), HttpStatus.OK);
	}
	
	// API to get reservation details by id
	@GetMapping("/{reservationId}")
	public ResponseEntity<Reservation> getReservationById(@PathVariable("reservationId")int reservationId) throws ReservationNotFoundException{
		return new ResponseEntity<Reservation>(reservationService.getReservationById(reservationId), HttpStatus.OK);
	}
	
	// API to delete a reservation by id
	@DeleteMapping("/delete/{reservationId}")
	public ResponseEntity<SuccessResponse> deleteReservationById(@PathVariable("reservationId")int reservationId) throws ReservationNotFoundException{
		throw new ReservationNotFoundException("DLTFAILS", "Reservation cannot be deleted");
	}
	
	// API to update a reservation by id
	@PutMapping("/update/{reservationId}")
	public ResponseEntity<SuccessResponse> updateReservationById(@RequestBody ReservationDTO reservation, @PathVariable("reservationId")int reservationId) throws ReservationNotFoundException, RoomNotFoundException{
		Reservation updateReservation = reservationService.getReservationById(reservationId);
		updateReservation.setGuest_name(reservation.getGuest_name());
		updateReservation.setGuest_phone(reservation.getGuest_phone());
		updateReservation.setGuest_email(reservation.getGuest_email());
		updateReservation.setCheck_in_date(reservation.getCheck_in_date());
		updateReservation.setCheck_out_date(reservation.getCheck_out_date());
		updateReservation.setRoom(roomService.getRoomById(reservation.getRoom_id()));
		return new ResponseEntity<SuccessResponse>(reservationService.updateReservationById(updateReservation), HttpStatus.OK);
	}
	
	// API to get the reservation in a given date range
	@GetMapping("/date-range")
	public ResponseEntity<List<ReservationDTO>> getReservationInSpecificRange(
			@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate startDate, 
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate endDate) throws ReservationNotFoundException, ParseException{

		return new ResponseEntity<List<ReservationDTO>>(reservationService.getReservationInDateRange(startDate, endDate), HttpStatus.OK);
	}
	
	// API to get all reservations details along with room id
	@GetMapping("/allwithrooms")
	public ResponseEntity<List<ReservationDTO>> getAllReservationsWithRoom() throws ReservationNotFoundException{
		return new ResponseEntity<List<ReservationDTO>>(reservationService.getAllReservationsWithRoom(), HttpStatus.OK);
	}
	
	// API to get all details of all reservations
	@GetMapping("/alldetails")
	public ResponseEntity<List<DetailsDTO>> getAllDetails(){
		return new ResponseEntity<List<DetailsDTO>>(reservationService.getAllDetails(), HttpStatus.OK);
	}
	
	// API to get reservation details along with room id by reservation id
	@GetMapping("/withroom/{reservationId}")
	public ResponseEntity<ReservationDTO> getReservationwithRoom(@PathVariable("reservationId") int reservationId ) throws ReservationNotFoundException{
		return new ResponseEntity<ReservationDTO>(reservationService.getReservationwithRoom(reservationId), HttpStatus.OK);
	}
	
	// API to get all details of a specific reservation by reservation id
	@GetMapping("/alldetails/{reservationId}")
	public ResponseEntity<List<DetailsDTO>> getAllDetailsByReservaitonId(@PathVariable("reservationId")int reservationId) throws ReservationNotFoundException{
		return new ResponseEntity<List<DetailsDTO>>(reservationService.getAllDetailsByReservationId(reservationId), HttpStatus.OK);
	}
	
	// API to get reservation details by guest email
	@GetMapping("/user/{guest_email}")
	public ResponseEntity<List<Reservation>> getAllReservationByUser(@PathVariable("guest_email") String guest_email) throws UserNotFoundException{
		return new ResponseEntity<List<Reservation>>(reservationService.getAllReservationByUser(guest_email), HttpStatus.OK);
	}
 
}