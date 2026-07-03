package com.movie.controller;

import com.movie.models.Review;
import com.movie.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    
    @PostMapping
    public Map<String, String> addReview(@RequestBody Review review) {
        String result = reviewService.addReview(review);
        if (result.equals("success")) {
            return Map.of("message", "Review Added!");
        }
        return Map.of("message", result);
    }

    
    @GetMapping("/movie/{movieId}")
    public List<Review> getReviews(@PathVariable String movieId) {
        return reviewService.getReviewsByMovie(movieId);
    }
}