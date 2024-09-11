package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SuccessResponse;
import com.example.demo.entity.Payment;
import com.example.demo.entity.User;
import com.example.demo.exception.HotelNotFoundException;
import com.example.demo.exception.PaymentNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService{

	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	UserRepository userRepository;
	
	// Method to create a payment
	@Override
	public SuccessResponse createPayment(Payment payment) throws PaymentNotFoundException {
		// TODO Auto-generated method stub
		if(paymentRepository.findById(payment.getPayment_id()).isEmpty()) {
			paymentRepository.save(payment);
			return new SuccessResponse("POSTSUCCESS", "Payment added successfully");
		} else
			return new SuccessResponse("ADDFAILS", "Payment already exist");
	}

	// Method to get all payments
	@Override
	public List<Payment> getAllPayments() throws PaymentNotFoundException {
		// TODO Auto-generated method stub
		if(paymentRepository.findAll().isEmpty())
			throw new PaymentNotFoundException("GETALLFAILS", "Payment list is empty");
		else
			return paymentRepository.findAll();
	}

	// Method to find payment by id
	@Override
	public Payment getPaymentById(int paymentId) throws PaymentNotFoundException {
		// TODO Auto-generated method stub
		if(paymentRepository.findById(paymentId).isEmpty())
			throw new PaymentNotFoundException("GETFAILS", "Payment doesn't exist");
		else
			return paymentRepository.findById(paymentId).get();
	}

	// Method to find payment by status
	@Override
	public List<Payment> getPaymentByStatus(String status) throws PaymentNotFoundException {
		// TODO Auto-generated method stub
		if(paymentRepository.getPaymentsByStatus(status).isEmpty())
			throw new PaymentNotFoundException("GETFAILS", "Payment list is empty");
		else
			return paymentRepository.getPaymentsByStatus(status);
	}

	// Method to get total revenue
	@Override
	public Double getTotalRevenue() {
		// TODO Auto-generated method stub
		return paymentRepository.getpaymentTotalRevenue();
	}
	
	// Method to delete a payment by id
	@Override
	public SuccessResponse deletePaymentById(int paymentId) throws PaymentNotFoundException {
		// TODO Auto-generated method stub
		if(paymentRepository.findById(paymentId).isEmpty())
			throw new PaymentNotFoundException("DLTFAILS", "Payment doesn't exist");
		else {
//			paymentRepository.deleteById(paymentId);
			return new SuccessResponse("DLTFAILS", "Payment cannot be deleted");
		}
	}
	
	// Method to get the recent payment details
	@Override
	public List<Payment> getRecentlyLastTranscation() {
		return paymentRepository.getRecentlyLastTranscation();
	}

	// Method to get payment details by guest email
	@Override
	public List<Payment> getPaymentByUserEmail(String guest_email) throws UserNotFoundException {
		User user= userRepository.validateEmail(guest_email);
		if(user!=null) {
			return paymentRepository.getPaymentByUserEmail(guest_email);
		}
		else {
			throw new UserNotFoundException("GETFAILS", "User doesn't exist");
		}
	}

}
