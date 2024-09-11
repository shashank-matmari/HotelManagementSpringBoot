package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.ReviewDTO;
import com.example.demo.dto.SuccessResponse;
import com.example.demo.entity.Review;
import com.example.demo.exception.ReviewNotFoundException;

public interface ReviewService {

	//Create Record
	SuccessResponse createReview(ReviewDTO reviewDto) throws ReviewNotFoundException;

	//getting all records
	List<Review> getAllReviews() throws ReviewNotFoundException;

	//getting record by id
	Review getReviewById(int review_id) throws ReviewNotFoundException;

	//Delete By Id
	SuccessResponse deleteReviewById(int review_id) throws ReviewNotFoundException;

	//Review By Rating
	List<Review> getReviewsByrating(int review_id)throws ReviewNotFoundException;

	//Recent Review
	List<Review> getRecentReviews() throws ReviewNotFoundException;

	//Update Review
	public SuccessResponse updateReview(int review_id,ReviewDTO reviewDTO) throws ReviewNotFoundException;


}
