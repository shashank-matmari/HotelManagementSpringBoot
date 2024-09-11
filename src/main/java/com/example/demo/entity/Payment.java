package com.example.demo.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="PAYMENT")
public class Payment {
	
	@Id
	@Column(name="PAYMENT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int payment_id;
	
	@NotNull(message = "Amount should not be null")
	@Column(name="AMOUNT",columnDefinition = "DECIMAL(10,2)")
	private double amount;
	
	@NotNull(message = "Payment Date should not be null")
	@Column(name="PAYMENT_DATE")
	private LocalDate payment_date;
	
	@Column(name="PAYMENT_STATUS")
	private String payment_status; 
	
	@ManyToOne
	@JoinColumn(name = "RESERVATION_ID", referencedColumnName = "RESERVATION_ID")
//	@JsonBackReference
	private Reservation reservation;
	
	@Column(name="PAYMENT_TYPE")
	private String payment_type;
	
	// Default Constructor
	public Payment() {
		
	}

	// Constructor with parameters
	public Payment(int payment_id, double amount, LocalDate payment_date, String payment_status, String payment_type) {
		super();
		this.payment_id = payment_id;
		this.amount = amount;
		this.payment_date = payment_date;
		this.payment_status = payment_status;
		this.payment_type=payment_type;
	}


	// Getters and Setters
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

	@JsonProperty("reservation")
	public Reservation getReservation() {
		return reservation!=null?new Reservation(reservation.getReservation_id(), reservation.getGuest_name(), reservation.getGuest_email(), reservation.getGuest_phone(), reservation.getCheck_in_date(), reservation.getCheck_out_date()):null;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}
	
	
}
