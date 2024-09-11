package com.example.demo.entity;
 
import java.time.LocalDate;
import java.util.List;
 
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
 
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
 
@Entity
@Table(name="RESERVATION")
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="RESERVATION_ID")
	private int reservation_id;
	
	@Column(name="GUEST_NAME")
	@NotNull(message = "Name should not be null")
	private String guest_name;
	
	@NotNull(message = "Email should not be null")
	@Pattern(regexp = "^[a-z0-9]{4,}@email.com$")
	@Column(name="GUEST_EMAIL")
	private String guest_email;
	
	@Column(name="GUEST_PHONE", columnDefinition = "VARCHAR(20)")
	@Pattern(regexp = "[0-9]{10}")
	private String guest_phone;
	
	@Column(name="CHECK_IN_DATE" ,columnDefinition = "DATE")
	@NotNull(message = "Check-in-date should not be null")
	private LocalDate check_in_date;
	
	@Column(name="CHECK_OUT_DATE",columnDefinition = "DATE")
	@NotNull(message = "Check-out-date should not be null")
	private LocalDate check_out_date;
	
	@OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Payment> payment;
	
	@OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Review> review;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROOM_ID")
	@JsonBackReference
	private Room room;
 
	// Default Constructor
	public Reservation() {
		super();
	}
 
	// Constructor with parameters
	public Reservation(int reservationId, String guestName, String guestEmail, String guestPhone, LocalDate checkInDate,
			LocalDate checkOutDate) {
		super();
		this.reservation_id = reservationId;
		this.guest_name = guestName;
		this.guest_email = guestEmail;
		this.guest_phone = guestPhone;
		this.check_in_date = checkInDate;
		this.check_out_date = checkOutDate;
	}
 
	// Getters and setters
	public int getReservation_id() {
		return reservation_id;
	}
 
	public void setReservation_id(int reservation_id) {
		this.reservation_id = reservation_id;
	}
 
	public String getGuest_name() {
		return guest_name;
	}
 
	public void setGuest_name(String guest_name) {
		this.guest_name = guest_name;
	}
 
	public String getGuest_email() {
		return guest_email;
	}
 
	public void setGuest_email(String guest_email) {
		this.guest_email = guest_email;
	}
 
	public String getGuest_phone() {
		return guest_phone;
	}
 
	public void setGuest_phone(String guest_phone) {
		this.guest_phone = guest_phone;
	}
 
	public LocalDate getCheck_in_date() {
		return check_in_date;
	}
 
	public void setCheck_in_date(LocalDate check_in_date) {
		this.check_in_date = check_in_date;
	}
 
	public LocalDate getCheck_out_date() {
		return check_out_date;
	}
 
	public void setCheck_out_date(LocalDate check_out_date) {
		this.check_out_date = check_out_date;
	}
 
	public List<Payment> getPayment() {
		return payment;
	}
 
	public void setPayment(List<Payment> payment) {
		this.payment = payment;
	}
 
	public Room getRoom() {
		return room;
	}
 
	public void setRoom(Room room) {
		this.room = room;
	}
 
	public List<Review> getReview() {
		return review;
	}
 
	public void setReview(List<Review> review) {
		this.review = review;
	}
 
	// To String method
	@Override
	public String toString() {
		return "Reservation [reservationId=" + reservation_id + ", guestName=" + guest_name + ", guestEmail=" + guest_email
				+ ", guestPhone=" + guest_phone + ", checkInDate=" + check_in_date + ", checkOutDate=" + check_out_date
				+ "]";
	}

 
}