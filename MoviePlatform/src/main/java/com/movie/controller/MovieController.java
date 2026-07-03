package com.movie.controller;

import com.movie.models.Movie;
import com.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
@CrossOrigin(origins = "*")
public class MovieController {

    @Autowired
    private MovieService movieService;

   
    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    
    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable String id) {
        return movieService.getMovieById(id);
    }

   
    @GetMapping("/search")
    public List<Movie> searchMovies(@RequestParam String title) {
        return movieService.searchByTitle(title);
    }

   
    @GetMapping("/genre/{genre}")
    public List<Movie> getByGenre(@PathVariable String genre) {
        return movieService.getByGenre(genre);
    }

   
    @GetMapping("/trending")
    public List<Movie> getTrending() {
        return movieService.getTopRated();
    }

    
    @PostMapping
    public Movie addMovie(@RequestBody Movie movie) {
        return movieService.addMovie(movie);
    }
}