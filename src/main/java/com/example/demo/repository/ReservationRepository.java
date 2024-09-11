package com.example.demo.repository;
 
import java.time.LocalDate;
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
 
import com.example.demo.dto.DetailsDTO;
import com.example.demo.dto.ReservationDTO;
import com.example.demo.entity.Reservation;
 
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer>{

	// Custom query to get reservations including room within the given date range
	@Query("select new com.example.demo.dto.ReservationDTO(r.reservation_id,r.guest_name,r.guest_email,r.guest_phone,r.check_in_date,r.check_out_date, r.room.room_id) from Reservation r where r.check_in_date>=:startDate and r.check_out_date<=:endDate")
	List<ReservationDTO> getReservationsInDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

	//	@Query("select r from Reservation r where r.room.room_id=:room_id")
	//	List<Room> getReservationsByRoomId(@Param("room_id") int room_id);
	
	// Custom query to get a list of all reservations along with room id
	@Query("select new com.example.demo.dto.ReservationDTO(r.reservation_id,r.guest_name,r.guest_email,r.guest_phone,r.check_in_date,r.check_out_date, r.room.room_id) from Reservation r")
	List<ReservationDTO> getAllReservationswithRoom();
	
	// Custom query to find a reservation along with room id by reservation id 
	@Query("select new com.example.demo.dto.ReservationDTO(r.reservation_id,r.guest_name,r.guest_email,r.guest_phone,r.check_in_date,r.check_out_date, r.room.room_id) from Reservation r where r.reservation_id=:reservationId")
	ReservationDTO getReservationwithRoom(@Param("reservationId") int reservationId);
	
	// Custom query to get hotel, amenity, room, room type, reservation, payment, review details of all reservations
	@Query("select r.room.hotel.hotel_id, r.room.hotel.name as hotel_name, r.room.hotel.location, r.room.hotel.description as hotel_description, "
			+ "a.amenity_id, a.name as amenity_name, a.description as amenity_description, "
			+ "r.room.room_id, r.room.room_number, r.room.is_available, "
			+ "r.reservation_id, r.guest_name, r.guest_email, r.guest_phone, r.check_in_date, r.check_out_date, "
			+ "p.payment_id, p.amount, p.payment_date, p.payment_status, "
			+ "re.review_id, re.rating, re.comment, re.review_date, "
			+ "r.room.roomType.room_type_id, r.room.roomType.type_name, r.room.roomType.description as room_description, r.room.roomType.max_occupancy, r.room.roomType.price_per_night"
			+ " from Reservation r join r.room.amenities a join r.payment p join r.review re")
	
	// Custom query to get hotel, amenity, room, room type, reservation, payment, review details of a reservation by reservation id
	List<Object[]> getAllDetails();
	@Query("select new com.example.demo.dto.DetailsDTO(r.room.hotel.hotel_id, r.room.hotel.name as hotel_name, r.room.hotel.location, r.room.hotel.description as hotel_description, "
			+ "a.amenity_id, a.name as amenity_name, a.description as amenity_description, "
			+ "r.room.room_id, r.room.room_number, r.room.is_available, "
			+ "r.reservation_id, r.guest_name, r.guest_email, r.guest_phone, r.check_in_date, r.check_out_date, "
			+ "p.payment_id, p.amount, p.payment_date, p.payment_status, "
			+ "re.review_id, re.rating, re.comment, re.review_date, "
			+ "r.room.roomType.room_type_id, r.room.roomType.type_name, r.room.roomType.description as room_description, r.room.roomType.max_occupancy, r.room.roomType.price_per_night)"
			+ " from Reservation r join r.room.amenities a join r.payment p join r.review re where r.reservation_id=:reservationId")
	List<DetailsDTO> getAllDetailsByReservationId(@Param("reservationId") int reservationId);
	
	//Custom query to get hotel, amenity, room, room type, reservation, payment details of a reservation by reservation id	
	@Query("select new com.example.demo.dto.DetailsDTO(r.room.hotel.hotel_id, r.room.hotel.name as hotel_name, r.room.hotel.location, r.room.hotel.description as hotel_description, "
			+ "a.amenity_id, a.name as amenity_name, a.description as amenity_description, "
			+ "r.room.room_id, r.room.room_number, r.room.is_available, "
			+ "r.reservation_id, r.guest_name, r.guest_email, r.guest_phone, r.check_in_date, r.check_out_date, "
			+ "p.payment_id, p.amount, p.payment_date, p.payment_status, "
			+ "r.room.roomType.room_type_id, r.room.roomType.type_name, r.room.roomType.description as room_description, r.room.roomType.max_occupancy, r.room.roomType.price_per_night)"
			+ " from Reservation r join r.room.amenities a join r.payment p where r.reservation_id=:reservationId")
	List<DetailsDTO> getDetailsByReservationIdWithoutReview(@Param("reservationId") int reservationId);
	
	// Custom query to find reservation details by guest email
	@Query("select r from Reservation r where r.guest_email=:guest_email")
	List<Reservation> getAllReservationByUser(@Param("guest_email") String guest_email);

}