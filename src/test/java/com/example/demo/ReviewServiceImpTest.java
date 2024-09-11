package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.dto.ReviewDTO;
import com.example.demo.dto.SuccessResponse;
import com.example.demo.entity.Reservation;
import com.example.demo.entity.Review;
import com.example.demo.exception.ReviewNotFoundException;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.services.ReviewServiceImp;

public class ReviewServiceImpTest {

    @InjectMocks
    private ReviewServiceImp reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    
    @Test
    public void testGetAllReviews() throws ReviewNotFoundException {
        Review review = new Review();
        when(reviewRepository.findAll()).thenReturn(Arrays.asList(review));

        List<Review> reviews = reviewService.getAllReviews();
        assertNotNull(reviews);
        assertEquals(1, reviews.size());
    }

    @Test
    public void testGetReviewById() throws ReviewNotFoundException {
        Review review = new Review();
        when(reviewRepository.findById(1)).thenReturn(Optional.of(review));

        Review foundReview = reviewService.getReviewById(1);
        assertNotNull(foundReview);
    }

    @Test
    public void testDeleteReviewById() throws ReviewNotFoundException {
        Review review = new Review();
        when(reviewRepository.findById(1)).thenReturn(Optional.of(review));

        SuccessResponse response = reviewService.deleteReviewById(1);
        assertNotNull(response);
        assertEquals("DELETESUCCESS", response.getCode());
    }

    @Test
    public void testGetReviewsByRating() throws ReviewNotFoundException {
        Review review = new Review();
        when(reviewRepository.findByRating(5)).thenReturn(Arrays.asList(review));

        List<Review> reviews = reviewService.getReviewsByrating(5);
        assertNotNull(reviews);
        assertEquals(1, reviews.size());
    }

    @Test
    public void testGetRecentReviews() throws ReviewNotFoundException {
        Review review = new Review();
        when(reviewRepository.findRecentReview()).thenReturn(Arrays.asList(review));

        List<Review> reviews = reviewService.getRecentReviews();
        assertNotNull(reviews);
        assertEquals(1, reviews.size());
    }

    @Test
    public void testCreateReview() throws ReviewNotFoundException {
        ReviewDTO reviewDto = new ReviewDTO();
        reviewDto.setReservation_id(1);
        reviewDto.setRating(5);
        reviewDto.setComment("Great!");
        reviewDto.setReview_date(LocalDate.now());

        Reservation reservation = new Reservation();
        when(reservationRepository.findById(1)).thenReturn(Optional.of(reservation));

        SuccessResponse response = reviewService.createReview(reviewDto);
        assertNotNull(response);
        assertEquals("POSTSUCCESS", response.getCode());
    }

    @Test
    public void testUpdateReview() throws ReviewNotFoundException {
        ReviewDTO reviewDto = new ReviewDTO();
        reviewDto.setReservation_id(1);
        reviewDto.setRating(5);
        reviewDto.setComment("Updated Comment");
        reviewDto.setReview_date(LocalDate.now());

        Review review = new Review();
        Reservation reservation = new Reservation();
        when(reviewRepository.findById(1)).thenReturn(Optional.of(review));
        when(reservationRepository.findById(1)).thenReturn(Optional.of(reservation));

        SuccessResponse response = reviewService.updateReview(1, reviewDto);
        assertNotNull(response);
        assertEquals("UPDATESUCCESS", response.getCode());
    }
}
