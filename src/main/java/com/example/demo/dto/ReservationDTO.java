package com.example.demo.dto;

import java.time.LocalDate;

public class ReservationDTO {
	
	private int reservation_id;
	private String guest_name;
	private String guest_email;
	private String guest_phone;
	private LocalDate check_in_date;
	private LocalDate check_out_date;
	private int room_id;
	
	// Default Constructor
	public ReservationDTO() {
		super();
	}	

	// Constructor with parameters
	public ReservationDTO(int reservation_id, String guest_name, String guest_email, String guest_phone,
			LocalDate check_in_date, LocalDate check_out_date, int room_id) {
		super();
		this.reservation_id = reservation_id;
		this.guest_name = guest_name;
		this.guest_email = guest_email;
		this.guest_phone = guest_phone;
		this.check_in_date = check_in_date;
		this.check_out_date = check_out_date;
		this.room_id = room_id;
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

	public int getRoom_id() {
		return room_id;
	}

	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}
	
}
