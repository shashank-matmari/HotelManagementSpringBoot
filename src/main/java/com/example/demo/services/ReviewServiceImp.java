package com.example.demo.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ReviewDTO;
import com.example.demo.dto.SuccessResponse;
import com.example.demo.entity.Reservation;
import com.example.demo.entity.Review;
import com.example.demo.exception.ReviewNotFoundException;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.ReviewRepository;


@Service
public class ReviewServiceImp implements ReviewService{

	@Autowired
	ReviewRepository reviewRepository;

	@Autowired
	ReservationRepository reservationRepository;

	// Method to get all reviews
	@Override
	public List<Review> getAllReviews() throws ReviewNotFoundException {

		if(reviewRepository.findAll().isEmpty()) {

			throw new ReviewNotFoundException("GETALLFAILS", "No Reviews are available");

		}
		return reviewRepository.findAll();
	}

	// Method to find review details by id
	@Override
	public Review getReviewById(int review_id) throws ReviewNotFoundException {

		if(reviewRepository.findById(review_id).isEmpty()) {

			throw new ReviewNotFoundException("GETFAILS", "review not found");
		}
		else 
			return reviewRepository.findById(review_id).get();

	}

	// Method to delete a review by id
	@Override
	public SuccessResponse deleteReviewById(int review_id) throws ReviewNotFoundException {

		if(reviewRepository.findById(review_id).isEmpty()) {

			throw new ReviewNotFoundException("DELFAILS", "Deletion is not possible ");
		}
		else {
			reviewRepository.deleteById(review_id);

			return new SuccessResponse("DELETESUCCESS", "review deleted successfully");
		}


	}

	// Method to find reviews by rating
	@Override
	public List<Review> getReviewsByrating(int rating) throws ReviewNotFoundException {

		if(reviewRepository.findByRating(rating).isEmpty()) {

			throw new ReviewNotFoundException("GETALLFAILS", "reviews are not found");
		}
		else {
			return reviewRepository.findByRating(rating);
		}
	}

	// Method to find the recent review given
	@Override
	public List<Review> getRecentReviews() throws ReviewNotFoundException {

		if(reviewRepository.findRecentReview().isEmpty()) {

			throw new ReviewNotFoundException("GETFAILS", "No recent reviews are available");
		}
		return reviewRepository.findRecentReview();
	}

	// Method to create a review 
	public SuccessResponse createReview(ReviewDTO reviewDto) throws ReviewNotFoundException {


		Optional<Reservation> reservation = reservationRepository.findById(reviewDto.getReservation_id());

		if(reservation.isEmpty()) {      

			throw new ReviewNotFoundException("ADDFAILS", "reservation already exist");
		}
		else {

			Review review = new Review();

			review.setRating(reviewDto.getRating());

			review.setComment(reviewDto.getComment());

			review.setReview_date(reviewDto.getReview_date());

			review.setReservation(reservation.get());

			reviewRepository.save(review);

		}
		return new SuccessResponse("POSTSUCCESS", "Review added successfully");

	}

	// Method to update a review
	@Override
	public SuccessResponse updateReview(int review_id,ReviewDTO reviewDto) throws ReviewNotFoundException {

		Optional<Review>Newreview=reviewRepository.findById(review_id);

		Optional<Reservation>reservation=reservationRepository.findById(reviewDto.getReservation_id());

		if(Newreview.isEmpty()) {

			throw new ReviewNotFoundException("UPDTFAILS", "review does not exist");

		}else if(reservation.isEmpty()) {

			throw new ReviewNotFoundException("UPDTFAILS", "reservation does not exist");

		}else {
			Review review=Newreview.get();

			review.setReview_id(review_id);

			review.setRating(reviewDto.getRating());

			review.setComment(reviewDto.getComment());

			review.setReview_date(reviewDto.getReview_date());

			review.setReservation(reservation.get());

			reviewRepository.save(review);
		}
		return new SuccessResponse("UPDATESUCCESS","Successfully Updated");

	}
}



