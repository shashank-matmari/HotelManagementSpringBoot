package com.example.demo.dto;

import java.time.LocalDate;

public class ReviewDTO {

	private int rating;
    private String comment;
    private LocalDate review_date;
    private int reservation_id;

    // Getters and Setters
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

	public int getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(int reservation_id) {
		this.reservation_id = reservation_id;
	}

}