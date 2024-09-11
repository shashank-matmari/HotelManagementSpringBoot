package com.example.demo.services;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.example.demo.dto.SuccessResponse;
import com.example.demo.entity.Payment;
import com.example.demo.exception.HotelNotFoundException;
import com.example.demo.exception.PaymentNotFoundException;
import com.example.demo.exception.UserNotFoundException;

public interface PaymentService {
	
	SuccessResponse createPayment(Payment payment) throws PaymentNotFoundException;
	
	List<Payment> getAllPayments() throws PaymentNotFoundException;

	Payment getPaymentById(int paymentId) throws PaymentNotFoundException;
	
	List<Payment> getPaymentByStatus(String status) throws PaymentNotFoundException;
	
	Double getTotalRevenue();
	
	SuccessResponse deletePaymentById(int paymentId) throws PaymentNotFoundException;
	
	public List<Payment> getRecentlyLastTranscation();
	
	List<Payment> getPaymentByUserEmail(String guest_email) throws UserNotFoundException;
	
}
