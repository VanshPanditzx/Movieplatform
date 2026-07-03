package com.movie.controller;

import com.movie.models.Movie;
import com.movie.service.OMDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/omdb")
@CrossOrigin(origins = "*")
public class OMDBController {

    @Autowired
    private OMDBService omdbService;

   
    @GetMapping("/test")
    public String testConnection() {
        return omdbService.testConnection();
    }

   
    @GetMapping("/popular")
    public List<Movie> getPopular() {
        return omdbService.getPopularMovies();
    }

  
    @GetMapping("/search")
    public List<Movie> search(
            @RequestParam String query) {
        return omdbService.searchMovies(query);
    }

   
    @GetMapping("/title")
    public Movie getByTitle(
            @RequestParam String title) {
        return omdbService.getMovieByTitle(title);
    }

   
    @GetMapping("/imdb/{imdbId}")
    public Movie getByImdbId(
            @PathVariable String imdbId) {
        return omdbService.getMovieByImdbId(imdbId);
    }

  
    @GetMapping("/genre/{genre}")
    public List<Movie> getByGenre(
            @PathVariable String genre) {
        return omdbService.getMoviesByGenre(genre);
    }
}