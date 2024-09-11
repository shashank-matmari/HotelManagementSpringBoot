package com.example.demo.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="REVIEW")
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="REVIEW_ID")
	private int review_id;
	
	
	@NotNull(message = "Rating should be in between 1 and 5")
	@Column(name="RATING")
	private int rating;
	
	@Column(name="COMMENT",columnDefinition = "TEXT")
	private String comment;
	
	@NotNull(message = "Review date should not be null")
	@Column(name="REVIEW_DATE",columnDefinition = "DATE")
	private LocalDate review_date;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RESERVATION_ID")
	@JsonBackReference
	private Reservation reservation;

	// Default Constructor
	public Review() {
		super();
	}

	// Constructor with parameters
	public Review(int reviewId, int rating, String comment, LocalDate reviewDate) {
		super();
		this.review_id = reviewId;
		this.rating = rating;
		this.comment = comment;
		this.review_date = reviewDate;
	}

	public int getReview_id() {
		return review_id;
	}

	public void setReview_id(int review_id) {
		this.review_id = review_id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDate getReview_date() {
		return review_date;
	}

	public void setReview_date(LocalDate review_date) {
		this.review_date = review_date;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	// To String
	@Override
	public String toString() {
		return "Review [reviewId=" + review_id + ", rating=" + rating + ", comment=" + comment + ", reviewDate="
				+ review_date + "]";
	}
	
	
}
