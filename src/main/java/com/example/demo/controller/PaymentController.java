package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PaymentDto;
import com.example.demo.dto.SuccessResponse;
import com.example.demo.entity.Payment;
import com.example.demo.entity.Reservation;
import com.example.demo.exception.PaymentNotFoundException;
import com.example.demo.exception.ReservationNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.services.PaymentService;

@RestController
@RequestMapping("/api/payment/")
@CrossOrigin("http://localhost:4200/")
public class PaymentController {

	@Autowired
	PaymentService paymentservice;

	@Autowired
	ReservationRepository reservationRepository;

	//Create a new payment
	@PostMapping("post")
	public ResponseEntity<SuccessResponse> createPayment(@RequestBody PaymentDto payment) throws PaymentNotFoundException ,ReservationNotFoundException {
		Payment createpayment=new Payment();
		Reservation reservation=reservationRepository.findById(payment.getReservation_id()).get();
		createpayment.setReservation(reservation);
		createpayment.setAmount(payment.getAmount());
		createpayment.setPayment_date(payment.getPayment_date());
		createpayment.setPayment_status(payment.getPayment_status());
		createpayment.setPayment_type(payment.getPayment_type());
		return new  ResponseEntity<SuccessResponse>(paymentservice.createPayment(createpayment),HttpStatus.CREATED);
	}

	//Get a list of all payments 
	@GetMapping("all")
	public ResponseEntity<List<Payment>> getAllPayments() throws PaymentNotFoundException {
		return new ResponseEntity<List<Payment>>(paymentservice.getAllPayments(), HttpStatus.OK);
	}

	//Get payment By Id
	@GetMapping("{payment_Id}")
	public ResponseEntity<Payment> getPaymentById(@PathVariable("payment_Id") int paymentId) throws PaymentNotFoundException {
		return new ResponseEntity<Payment>(paymentservice.getPaymentById(paymentId), HttpStatus.OK);
	}

	//Retrieve payments based on their status (e.g., "Paid," "Pending").
	@GetMapping("status/{statusname}")
	public ResponseEntity<List<Payment>> getPaymentsByStatus(@PathVariable("statusname") String status) throws PaymentNotFoundException {
		return new ResponseEntity<List<Payment>>(paymentservice.getPaymentByStatus(status), HttpStatus.OK);
	}

	//Retrieve the total revenue generated through payments.
	@GetMapping("total-revenue")
	public ResponseEntity<Double> getpaymentTotalRevenue() throws PaymentNotFoundException {
		return new ResponseEntity<Double>(paymentservice.getTotalRevenue(), HttpStatus.OK);
	}

	//Delete a payment 
	@DeleteMapping("delete/{payment_Id}")
	public ResponseEntity<SuccessResponse> deletePaymentById(@PathVariable("payment_Id") int payment_Id) throws PaymentNotFoundException {
		return new ResponseEntity<SuccessResponse>(paymentservice.deletePaymentById(payment_Id), HttpStatus.OK);
		//		throw new PaymentNotFoundException("DLTFAILS", "Payment cannot be deleted");
	}
	
	//Get payment By Id
	@GetMapping("recent_payment")
	public ResponseEntity<List<Payment>> getRecentlyLastTranscation() {
		return new ResponseEntity<List<Payment>>(paymentservice.getRecentlyLastTranscation(), HttpStatus.OK);
	}
	
	@GetMapping("/user/{guest_email}")
	public ResponseEntity<List<Payment>> getAllReservationByUser(@PathVariable("guest_email") String guest_email) throws UserNotFoundException{
		return new ResponseEntity<List<Payment>>(paymentservice.getPaymentByUserEmail(guest_email), HttpStatus.OK);
	}

}