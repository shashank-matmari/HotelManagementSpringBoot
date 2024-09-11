package com.example.demo.services;
 
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.example.demo.dto.DetailsDTO;
import com.example.demo.dto.ReservationDTO;
import com.example.demo.dto.SuccessResponse;
import com.example.demo.entity.Reservation;
import com.example.demo.entity.User;
import com.example.demo.exception.ReservationNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.UserRepository;
 
@Service
public class ReservationServiceImpl implements ReservationService{
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	UserRepository userRepository;
 
	// Method to create a reservation
	@Override
	public Reservation createReservation(Reservation reservation) throws ReservationNotFoundException{
		// TODO Auto-generated method stub
//		System.out.println(reservationRepository.getReservationsByRoomId(reservation.getRoom().getRoom_id()));
		if(reservation.getGuest_phone().length()!=10 || reservation.getCheck_out_date().isBefore(reservation.getCheck_in_date())) {
			throw new ReservationNotFoundException("ADDFAILS", "Invalid reservation details");
		}
		else {
			return reservationRepository.save(reservation);
//			return new SuccessResponse("POSTSUCCESS", "Reservation added successfully");
		}
	}	
 
	// Method to get all reservations
	@Override
	public List<Reservation> getAllReservations() throws ReservationNotFoundException {
		// TODO Auto-generated method stub
		if(reservationRepository.findAll().isEmpty())
			throw new ReservationNotFoundException("GETALLFAILS","reservation list is empty");
		else
			return reservationRepository.findAll();
	}
 
	// Method to get a reservation details by ID
	@Override
	public Reservation getReservationById(int reservationId) throws ReservationNotFoundException {
		// TODO Auto-generated method stub
		if(reservationRepository.findById(reservationId).isEmpty())
			throw new ReservationNotFoundException("GETFAILS", "Reservation doesn't exist");
		else
			return reservationRepository.findById(reservationId).get();
	}
 
	// Method to delete a reservation by id
	@Override
	public SuccessResponse deleteReservationById(int reservationId) throws ReservationNotFoundException {
		// TODO Auto-generated method stub
		if(reservationRepository.findById(reservationId).isEmpty()) {
			throw new ReservationNotFoundException("DLTFAILS", "Reservation doesn't exist");
		} else {
			throw new ReservationNotFoundException("DLTFAILS", "Reservation cannot be deleted");
		}
	}
 
	// Method to update a reservation details by id
	@Override
	public SuccessResponse updateReservationById(Reservation reservation) throws ReservationNotFoundException {
		// TODO Auto-generated method stub
		if(reservationRepository.findById(reservation.getReservation_id()).isEmpty())
			throw new ReservationNotFoundException("UPDTFAILS","Reservation doesn't exist exist");
		else {
			reservationRepository.save(reservation);
			return new SuccessResponse("UPDATESUCCESS", "Reservation updated successfully");			
		}
	}
 
	// Method to get reservations in a given date range
	@Override
	public List<ReservationDTO> getReservationInDateRange(LocalDate startDate, LocalDate endDate) throws ReservationNotFoundException {
		// TODO Auto-generated method stub
		if(reservationRepository.getReservationsInDateRange(startDate, endDate).isEmpty())
			throw new ReservationNotFoundException("GETFAILS", "Reservations doesn't exist in specified range");
		else
			return reservationRepository.getReservationsInDateRange(startDate, endDate);
	}
 
	// Method to get all reservation details along with room id
	@Override
	public List<ReservationDTO> getAllReservationsWithRoom() throws ReservationNotFoundException {
		// TODO Auto-generated method stub
		if(reservationRepository.getAllReservationswithRoom().isEmpty())
			throw new ReservationNotFoundException("GETFAILS", "Reservation list is empty");
		else
			return reservationRepository.getAllReservationswithRoom();
	}
 
	// Method to get all details of all reservations
	@Override
	public List<DetailsDTO> getAllDetails() {
		// TODO Auto-generated method stub
		List<DetailsDTO> allDetails = new ArrayList<>();
		reservationRepository.getAllDetails().stream().forEach(det->{
			allDetails.add(new DetailsDTO((int) det[0],(String) det[1], (String) det[2], (String) det[3], (int) det[4], (String) det[5], (String) det[6], (int) det[7], (int) det[8], (boolean) det[9], (int) det[10], (String) det[11], (String) det[12], (String) det[13], (LocalDate) det[14], (LocalDate) det[15], (int) det[16], (double) det[17], (LocalDate) det[18], (String) det[19], (int) det[20], (int) det[21], (String) det[22], (LocalDate) det[23], (int) det[24], (String) det[25], (String) det[26], (int) det[27], (double) det[28]));
		});
		return allDetails;
	}
 
	// Method to get reservation details along with room id by reservation id
	@Override
	public ReservationDTO getReservationwithRoom(int reservationId) throws ReservationNotFoundException {
		// TODO Auto-generated method stub
		if(reservationRepository.getReservationwithRoom(reservationId) != null)
			return reservationRepository.getReservationwithRoom(reservationId);
		else
			throw new ReservationNotFoundException("GETFAILS", "Reservation doesn't exist");
 
	}
 
	// Method to get all details of a reservation by reservation id
	@Override
	public List<DetailsDTO> getAllDetailsByReservationId(int reservationId) throws ReservationNotFoundException {
		// TODO Auto-generated method stub
		List<DetailsDTO> details = new ArrayList<>();
		if(reservationRepository.getDetailsByReservationIdWithoutReview(reservationId).isEmpty() )
			throw new ReservationNotFoundException("GETFAILS", "Reservation doesn't exist");
		else {
			reservationRepository.getAllDetailsByReservationId(reservationId).stream().forEach(det->{
				details.add(det);
			});
		}
		if(details.isEmpty()) {
			reservationRepository.getDetailsByReservationIdWithoutReview(reservationId).stream().forEach(det->{
				details.add(det);
			});
		}
 
		return details;
	}
	
	// Method to get reservation details by guest email
	@Override
	public List<Reservation> getAllReservationByUser(String email) throws UserNotFoundException{
		User user=userRepository.validateEmail(email);
		if(user!=null) {
			return reservationRepository.getAllReservationByUser(email);
		}else {
			throw new UserNotFoundException("GETFAILS", "User doesn't exist");
		}
	}
 
}