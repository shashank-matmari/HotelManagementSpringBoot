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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.ReviewService;
import com.example.demo.dto.ReviewDTO;
import com.example.demo.dto.SuccessResponse;
import com.example.demo.entity.Review;
import com.example.demo.exception.ReviewNotFoundException;


@RestController
@RequestMapping("/api/review/")
@CrossOrigin("http://localhost:4200/")
public class ReviewController {
	
	@Autowired
	ReviewService reviewService;
	
	// API to get all reviews
	@GetMapping("all")
	public ResponseEntity<List<Review>> getAll() throws ReviewNotFoundException{
		return new ResponseEntity<List<Review>>(reviewService.getAllReviews(),HttpStatus.OK);
	}
	
	// API to get review details by id
	@GetMapping("{review_id}")
	public ResponseEntity<Review> getReviewById(@PathVariable("review_id")int id) throws ReviewNotFoundException{
		return new ResponseEntity<Review>(reviewService.getReviewById(id),HttpStatus.OK);
	}
	
	// API to delete a review by id
	@DeleteMapping("delete/{review_id}")
	public ResponseEntity<SuccessResponse> DeleteByid(@PathVariable("review_id") int review_id) throws ReviewNotFoundException{
		return new ResponseEntity<SuccessResponse>(reviewService.deleteReviewById(review_id),HttpStatus.OK);
	}
	
	// API to get review details by rating
	@GetMapping("rating/{rating}")
	public ResponseEntity<List<Review>>getReviewByrating(@PathVariable("rating")int rating) throws ReviewNotFoundException{
		return new ResponseEntity<List<Review>>(reviewService.getReviewsByrating(rating),HttpStatus.OK);
	}
	
	// API to fetch recent review given
	@GetMapping("recent")
	public ResponseEntity<List<Review>>getRecentReview() throws ReviewNotFoundException{
		return new ResponseEntity<List<Review>>(reviewService.getRecentReviews(),HttpStatus.OK);
	}
	
	// API to create a review
	@PostMapping("post")
	public ResponseEntity<SuccessResponse> createReview(@RequestBody ReviewDTO reviewDTO) throws ReviewNotFoundException {
		return new ResponseEntity<SuccessResponse>(reviewService.createReview(reviewDTO), HttpStatus.CREATED);
	}
	
	// API to update a review by id
	@PutMapping("update/{review_id}")
	public ResponseEntity<SuccessResponse>update(@PathVariable("review_id")int review_id ,@RequestBody ReviewDTO reviewdto) throws ReviewNotFoundException{
		return new ResponseEntity<SuccessResponse>(reviewService.updateReview(review_id, reviewdto),HttpStatus.OK);
	}
}
