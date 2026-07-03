package com.movie.repository;

import com.movie.models.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ReviewRepository
        extends MongoRepository<Review, String> {

    
    List<Review> findByMovieId(String movieId);

 
    Review findByMovieIdAndUserId(String movieId, String userId);
}