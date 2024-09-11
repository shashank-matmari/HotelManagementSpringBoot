package com.example.demo.repository;
 
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
 
import com.example.demo.entity.Payment;
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

	//Custom query to find payments by payment status
	@Query("select p from Payment p where p.payment_status = :status")
	List<Payment> getPaymentsByStatus(String status);
 
	// Custom query to find the total revenue
	@Query("select sum(p.amount) from Payment as p")
	Double getpaymentTotalRevenue();
	
	// Custom query to find the latest payment done
	@Query("select p from Payment p order by p.payment_date desc limit 10")
	List<Payment> getRecentlyLastTranscation();
	
	// Custom query to find the payment details by guest email in reservation entity
	@Query("select p from Payment p where p.reservation.guest_email=:guest_email")
	List<Payment> getPaymentByUserEmail(@Param("guest_email") String guest_email);
 
 
}