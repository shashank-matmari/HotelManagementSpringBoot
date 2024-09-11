package com.example.demo;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.dto.SuccessResponse;
import com.example.demo.entity.Payment;
import com.example.demo.exception.PaymentNotFoundException;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.services.PaymentServiceImpl;

public class PaymentServiceImplTest {

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePayment() throws PaymentNotFoundException {
        Payment payment = new Payment();
        payment.setPayment_id(1);

        when(paymentRepository.findById(1)).thenReturn(Optional.empty());

        SuccessResponse response = paymentService.createPayment(payment);
        assertNotNull(response);
        assertEquals("POSTSUCCESS", response.getCode());

        verify(paymentRepository, times(1)).save(payment);
    }

    @Test
    public void testCreatePaymentAlreadyExists() throws PaymentNotFoundException {
        Payment payment = new Payment();
        payment.setPayment_id(1);

        when(paymentRepository.findById(1)).thenReturn(Optional.of(payment));

        SuccessResponse response = paymentService.createPayment(payment);
        assertNotNull(response);
        assertEquals("ADDFAILS", response.getCode());

        verify(paymentRepository, never()).save(payment);
    }

    @Test
    public void testGetAllPayments() throws PaymentNotFoundException {
        Payment payment = new Payment();
        when(paymentRepository.findAll()).thenReturn(Arrays.asList(payment));

        List<Payment> payments = paymentService.getAllPayments();
        assertNotNull(payments);
        assertEquals(1, payments.size());
    }

    @Test
    public void testGetAllPaymentsEmpty() {
        when(paymentRepository.findAll()).thenReturn(Arrays.asList());

        assertThrows(PaymentNotFoundException.class, () -> {
            paymentService.getAllPayments();
        });
    }

    @Test
    public void testGetPaymentById() throws PaymentNotFoundException {
        Payment payment = new Payment();
        when(paymentRepository.findById(1)).thenReturn(Optional.of(payment));

        Payment foundPayment = paymentService.getPaymentById(1);
        assertNotNull(foundPayment);
    }

    @Test
    public void testGetPaymentByIdNotFound() {
        when(paymentRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(PaymentNotFoundException.class, () -> {
            paymentService.getPaymentById(1);
        });
    }

    @Test
    public void testGetPaymentByStatus() throws PaymentNotFoundException {
        Payment payment = new Payment();
        when(paymentRepository.getPaymentsByStatus("Completed")).thenReturn(Arrays.asList(payment));

        List<Payment> payments = paymentService.getPaymentByStatus("Completed");
        assertNotNull(payments);
        assertEquals(1, payments.size());
    }

    @Test
    public void testGetPaymentByStatusNotFound() {
        when(paymentRepository.getPaymentsByStatus("Completed")).thenReturn(Arrays.asList());

        assertThrows(PaymentNotFoundException.class, () -> {
            paymentService.getPaymentByStatus("Completed");
        });
    }

    @Test
    public void testGetTotalRevenue() {
        when(paymentRepository.getpaymentTotalRevenue()).thenReturn(1000.0);

        Double totalRevenue = paymentService.getTotalRevenue();
        assertNotNull(totalRevenue);
        assertEquals(1000.0, totalRevenue);
    }

    @Test
    public void testDeletePaymentById() {
        when(paymentRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(PaymentNotFoundException.class, () -> {
            paymentService.deletePaymentById(1);
        });
    }

    @Test
    public void testGetRecentlyLastTranscation() {
        Payment payment = new Payment();
        when(paymentRepository.getRecentlyLastTranscation()).thenReturn(Arrays.asList(payment));

        List<Payment> payments = paymentService.getRecentlyLastTranscation();
        assertNotNull(payments);
        assertEquals(1, payments.size());
    }
}
