package com.movie.repository;

import com.movie.models.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface MovieRepository
        extends MongoRepository<Movie, String> {

  
    List<Movie> findByGenreIgnoreCase(String genre);

   
    List<Movie> findByTitleContainingIgnoreCase(String title);

   
    List<Movie> findTop5ByOrderByRatingDesc();
}