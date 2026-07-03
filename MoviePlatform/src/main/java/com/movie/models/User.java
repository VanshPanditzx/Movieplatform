package com.movie.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
public class User {

    @Id
    private String id;

    @Field("username")
    private String username;

    @Field("email")
    private String email;

    @Field("password")
    private String password;

    @Field("favoriteGenre")
    private String favoriteGenre;

    @Field("watchlist")
    private List<String> watchlist;

    
    public User() {
        this.watchlist = new ArrayList<>();
    }

    public User(String username, String email,
                String password, String favoriteGenre) {
        this.username      = username;
        this.email         = email;
        this.password      = password;
        this.favoriteGenre = favoriteGenre;
        this.watchlist     = new ArrayList<>();
    }

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFavoriteGenre() {
        return favoriteGenre;
    }

    public void setFavoriteGenre(String favoriteGenre) {
        this.favoriteGenre = favoriteGenre;
    }

    public List<String> getWatchlist() {
        if (this.watchlist == null) {
            this.watchlist = new ArrayList<>();
        }
        return watchlist;
    }

    public void setWatchlist(List<String> watchlist) {
        this.watchlist = watchlist;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", favoriteGenre='" + favoriteGenre + '\'' +
                ", watchlist=" + watchlist +
                '}';
    }
}