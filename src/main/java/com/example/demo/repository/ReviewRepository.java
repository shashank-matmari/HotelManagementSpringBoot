package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Review;

@Repository 
public interface ReviewRepository extends JpaRepository<Review, Integer> {
	
	// Custom query to find reviews by specific rating
	@Query("select r from Review r where r.rating=:rating")
	public List<Review>findByRating(int rating);

	// Custom query to find the latest review given
	@Query("select r from Review r order by r.review_date desc")
	public List<Review>findRecentReview();



}
