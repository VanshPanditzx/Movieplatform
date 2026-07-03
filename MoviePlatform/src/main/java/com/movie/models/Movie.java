package com.movie.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "movies")
public class Movie {

    @Id
    private String id;
    private String imdbId;         
    private String title;
    private String genre;
    private String director;
    private String actors;        
    private int releaseYear;
    private double rating;        
    private int totalReviews;
    private String description;   
    private String posterPath;     
    private String imdbRating;     
    private String rated;         
    private String runtime;       
    private String language;
    private String country;
    private String awards;

   
    public Movie() {}

   
    public String getId()                     { return id; }
    public void setId(String id)              { this.id = id; }

    public String getImdbId()                 { return imdbId; }
    public void setImdbId(String imdbId)      { this.imdbId = imdbId; }

    public String getTitle()                  { return title; }
    public void setTitle(String title)        { this.title = title; }

    public String getGenre()                  { return genre; }
    public void setGenre(String genre)        { this.genre = genre; }

    public String getDirector()               { return director; }
    public void setDirector(String director)  { this.director = director; }

    public String getActors()                 { return actors; }
    public void setActors(String actors)      { this.actors = actors; }

    public int getReleaseYear()               { return releaseYear; }
    public void setReleaseYear(int y)         { this.releaseYear = y; }

    public double getRating()                 { return rating; }
    public void setRating(double rating)      { this.rating = rating; }

    public int getTotalReviews()              { return totalReviews; }
    public void setTotalReviews(int t)        { this.totalReviews = t; }

    public String getDescription()            { return description; }
    public void setDescription(String d)      { this.description = d; }

    public String getPosterPath()             { return posterPath; }
    public void setPosterPath(String p)       { this.posterPath = p; }

    public String getImdbRating()             { return imdbRating; }
    public void setImdbRating(String r)       { this.imdbRating = r; }

    public String getRated()                  { return rated; }
    public void setRated(String rated)        { this.rated = rated; }

    public String getRuntime()                { return runtime; }
    public void setRuntime(String runtime)    { this.runtime = runtime; }

    public String getLanguage()               { return language; }
    public void setLanguage(String language)  { this.language = language; }

    public String getCountry()                { return country; }
    public void setCountry(String country)    { this.country = country; }

    public String getAwards()                 { return awards; }
    public void setAwards(String awards)      { this.awards = awards; }
}