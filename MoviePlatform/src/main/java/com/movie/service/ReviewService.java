package com.movie.service;

import com.movie.models.Review;
import com.movie.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MovieService movieService;

    // Add review
    public String addReview(Review review) {
        // Check already reviewed
        Review existing = reviewRepository
                .findByMovieIdAndUserId(
                        review.getMovieId(),
                        review.getUserId()
                );

        if (existing != null) {
            return "Already reviewed!";
        }

        reviewRepository.save(review);
        updateAverageRating(review.getMovieId());
        return "success";
    }

  
    public List<Review> getReviewsByMovie(String movieId) {
        return reviewRepository.findByMovieId(movieId);
    }

   
    private void updateAverageRating(String movieId) {
        List<Review> reviews = getReviewsByMovie(movieId);
        if (reviews.isEmpty()) return;

        double total = 0;
        for (Review r : reviews) total += r.getRating();
        double avg = Math.round((total / reviews.size()) * 10.0) / 10.0;

        movieService.updateRating(movieId, avg, reviews.size());
    }
}