package com.movie.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document(collection = "reviews")
public class Review {

    @Id
    private String id;
    private String movieId;
    private String userId;
    private String username;
    private int rating;
    private String comment;
    private String reviewDate;

    public Review() {}

    public Review(String movieId, String userId,
                  String username, int rating, String comment) {
        this.movieId    = movieId;
        this.userId     = userId;
        this.username   = username;
        this.rating     = rating;
        this.comment    = comment;
        this.reviewDate = LocalDate.now().toString();
    }

    
    public String getId()                      { return id; }
    public void setId(String id)               { this.id = id; }

    public String getMovieId()                 { return movieId; }
    public void setMovieId(String movieId)     { this.movieId = movieId; }

    public String getUserId()                  { return userId; }
    public void setUserId(String userId)       { this.userId = userId; }

    public String getUsername()                { return username; }
    public void setUsername(String username)   { this.username = username; }

    public int getRating()                     { return rating; }
    public void setRating(int rating)          { this.rating = rating; }

    public String getComment()                 { return comment; }
    public void setComment(String comment)     { this.comment = comment; }

    public String getReviewDate()              { return reviewDate; }
    public void setReviewDate(String date)     { this.reviewDate = date; }
}