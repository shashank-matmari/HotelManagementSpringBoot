package com.example.demo.dto;
 
import java.time.LocalDate;
 
public class DetailsDTO {
	private int hotel_id;
	private String hotel_name;
	private String location;
	private String hotel_description;
	private int amenity_id;
	private String amenity_name;
	private String amenity_description;
	private int room_id;
	private int room_number;
	private boolean is_available;
	private int reservation_id;
	private String guest_name;
	private String guest_email;
	private String guest_phone;
	private LocalDate check_in_date;
	private LocalDate check_out_date;
	private int payment_id;
	private double amount;
	private LocalDate payment_date;
	private String payment_status;
	private int review_id;
	private int rating;
	private String comment;
	private LocalDate review_date;
	private int room_type_id;
	private String type_name;
	private String room_description;
	private int max_occupancy;
	private double price_per_night;
	
	// Getters and setters
	public int getHotel_id() {
		return hotel_id;
	}
	public void setHotel_id(int hotel_id) {
		this.hotel_id = hotel_id;
	}
	public String getHotel_name() {
		return hotel_name;
	}
	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getHotel_description() {
		return hotel_description;
	}
	public void setHotel_description(String hotel_description) {
		this.hotel_description = hotel_description;
	}
	public int getAmenity_id() {
		return amenity_id;
	}
	public void setAmenity_id(int amenity_id) {
		this.amenity_id = amenity_id;
	}
	public String getAmenity_name() {
		return amenity_name;
	}
	public void setAmenity_name(String amenity_name) {
		this.amenity_name = amenity_name;
	}
	public String getAmenity_description() {
		return amenity_description;
	}
	public void setAmenity_description(String amenity_description) {
		this.amenity_description = amenity_description;
	}
	public int getRoom_id() {
		return room_id;
	}
	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}
	public int getRoom_number() {
		return room_number;
	}
	public void setRoom_number(int room_number) {
		this.room_number = room_number;
	}
	public boolean isIs_available() {
		return is_available;
	}
	public void setIs_available(boolean is_available) {
		this.is_available = is_available;
	}
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
	public int getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDate getPayment_date() {
		return payment_date;
	}
	public void setPayment_date(LocalDate payment_date) {
		this.payment_date = payment_date;
	}
	public String getPayment_status() {
		return payment_status;
	}
	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}
	public int getReview_id() {
		return review_id;
	}
	public void setReview_id(int review_id) {
		this.review_id = review_id;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public LocalDate getReview_date() {
		return review_date;
	}
	public void setReview_date(LocalDate review_date) {
		this.review_date = review_date;
	}
	public int getRoom_type_id() {
		return room_type_id;
	}
	public void setRoom_type_id(int room_type_id) {
		this.room_type_id = room_type_id;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public String getRoom_description() {
		return room_description;
	}
	public void setRoom_description(String room_description) {
		this.room_description = room_description;
	}
	public int getMax_occupancy() {
		return max_occupancy;
	}
	public void setMax_occupancy(int max_occupancy) {
		this.max_occupancy = max_occupancy;
	}
	public double getPrice_per_night() {
		return price_per_night;
	}
	public void setPrice_per_night(double price_per_night) {
		this.price_per_night = price_per_night;
	}
	
	// Default constructor
	public DetailsDTO() {
		super();
	}
	
	// Constructor with parameters
	public DetailsDTO(int hotel_id, String hotel_name, String location, String hotel_description, int amenity_id,
			String amenity_name, String amenity_description, int room_id, int room_number, boolean is_available,
			int reservation_id, String guest_name, String guest_email, String guest_phone, LocalDate check_in_date,
			LocalDate check_out_date, int payment_id, double amount, LocalDate payment_date, String payment_status, int review_id,
			int rating, String comment, LocalDate review_date, int room_type_id, String type_name, String room_description,
			int max_occupancy, double price_per_night) {
		super();
		this.hotel_id = hotel_id;
		this.hotel_name = hotel_name;
		this.location = location;
		this.hotel_description = hotel_description;
		this.amenity_id = amenity_id;
		this.amenity_name = amenity_name;
		this.amenity_description = amenity_description;
		this.room_id = room_id;
		this.room_number = room_number;
		this.is_available = is_available;
		this.reservation_id = reservation_id;
		this.guest_name = guest_name;
		this.guest_email = guest_email;
		this.guest_phone = guest_phone;
		this.check_in_date = check_in_date;
		this.check_out_date = check_out_date;
		this.payment_id = payment_id;
		this.amount = amount;
		this.payment_date = payment_date;
		this.payment_status = payment_status;
		this.review_id = review_id;
		this.rating = rating;
		this.comment = comment;
		this.review_date = review_date;
		this.room_type_id = room_type_id;
		this.type_name = type_name;
		this.room_description = room_description;
		this.max_occupancy = max_occupancy;
		this.price_per_night = price_per_night;
	}

	// Constructor with parameters except review details
	public DetailsDTO(int hotel_id, String hotel_name, String location, String hotel_description, int amenity_id,
			String amenity_name, String amenity_description, int room_id, int room_number, boolean is_available,
			int reservation_id, String guest_name, String guest_email, String guest_phone, LocalDate check_in_date,
			LocalDate check_out_date, int payment_id, double amount, LocalDate payment_date, String payment_status,
			int room_type_id, String type_name, String room_description, int max_occupancy, double price_per_night) {
		super();
		this.hotel_id = hotel_id;
		this.hotel_name = hotel_name;
		this.location = location;
		this.hotel_description = hotel_description;
		this.amenity_id = amenity_id;
		this.amenity_name = amenity_name;
		this.amenity_description = amenity_description;
		this.room_id = room_id;
		this.room_number = room_number;
		this.is_available = is_available;
		this.reservation_id = reservation_id;
		this.guest_name = guest_name;
		this.guest_email = guest_email;
		this.guest_phone = guest_phone;
		this.check_in_date = check_in_date;
		this.check_out_date = check_out_date;
		this.payment_id = payment_id;
		this.amount = amount;
		this.payment_date = payment_date;
		this.payment_status = payment_status;
		this.room_type_id = room_type_id;
		this.type_name = type_name;
		this.room_description = room_description;
		this.max_occupancy = max_occupancy;
		this.price_per_night = price_per_night;
	}

	// To String Method
	@Override
	public String toString() {
		return "DetailsDTO [hotel_id=" + hotel_id + ", hotel_name=" + hotel_name + ", location=" + location
				+ ", hotel_description=" + hotel_description + ", amenity_id=" + amenity_id + ", amenity_name="
				+ amenity_name + ", amenity_description=" + amenity_description + ", room_id=" + room_id
				+ ", room_number=" + room_number + ", is_available=" + is_available + ", reservation_id="
				+ reservation_id + ", guest_name=" + guest_name + ", guest_email=" + guest_email + ", guest_phone="
				+ guest_phone + ", check_in_date=" + check_in_date + ", check_out_date=" + check_out_date
				+ ", payment_id=" + payment_id + ", amount=" + amount + ", payment_date=" + payment_date
				+ ", payment_status=" + payment_status + ", review_id=" + review_id + ", rating=" + rating
				+ ", comment=" + comment + ", review_date=" + review_date + ", room_type_id=" + room_type_id
				+ ", type_name=" + type_name + ", room_description=" + room_description + ", max_occupancy="
				+ max_occupancy + ", price_per_night=" + price_per_night + "]";
	}


 
}