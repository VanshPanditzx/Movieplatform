package com.movie.service;

import com.movie.models.Movie;
import com.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

   
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

   
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    
    public List<Movie> searchByTitle(String title) {
        return movieRepository
                .findByTitleContainingIgnoreCase(title);
    }


    public List<Movie> getByGenre(String genre) {
        return movieRepository.findByGenreIgnoreCase(genre);
    }

    
    public List<Movie> getTopRated() {
        return movieRepository.findTop5ByOrderByRatingDesc();
    }

   
    public Movie getMovieById(String id) {
        return movieRepository.findById(id).orElse(null);
    }

   
    public void updateRating(String movieId,
                              double rating, int total) {
        Movie movie = getMovieById(movieId);
        if (movie != null) {
            movie.setRating(rating);
            movie.setTotalReviews(total);
            movieRepository.save(movie);
        }
    }
}