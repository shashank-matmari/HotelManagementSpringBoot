package com.example.demo.dto;

import java.time.LocalDate;

public class PaymentDto {
	
	private int reservation_id;
	private double amount;
	private LocalDate payment_date;
	private String payment_status;
	private String payment_type;
	
	// Default Constructor
	public PaymentDto() {
		super();
	}

	// Constructor with parameters
	public PaymentDto(int reservation_id, double amount, LocalDate payment_date, String payment_status, String payment_type) {
		super();
		this.reservation_id = reservation_id;
		this.amount = amount;
		this.payment_date = payment_date;
		this.payment_status = payment_status;
		this.payment_type=payment_type;
	}

	// Getters and setters
	public int getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(int reservation_id) {
		this.reservation_id = reservation_id;
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

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

}
