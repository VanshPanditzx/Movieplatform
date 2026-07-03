package com.movie.controller;

import com.movie.models.User;
import com.movie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    
    @PostMapping("/register")
    public Map<String, String> register(
            @RequestBody User user) {

        System.out.println("\n📥 REGISTER REQUEST RECEIVED");
        System.out.println("Username: " + user.getUsername());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Password: " + user.getPassword());
        System.out.println("Genre: " + user.getFavoriteGenre());

        Map<String, String> response = new HashMap<>();

        String result = userService.registerUser(user);

        if (result.equals("success")) {
            response.put("message", "Registered Successfully!");
            System.out.println("📤 SUCCESS RESPONSE SENT");
        } else {
            response.put("message", result);
            System.out.println("📤 ERROR RESPONSE: " + result);
        }

        return response;
    }

   
    @PostMapping("/login")
    public Map<String, Object> login(
            @RequestBody Map<String, String> body) {

        String email = body.get("email");
        String password = body.get("password");

        System.out.println("\n📥 LOGIN REQUEST RECEIVED");
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);

        Map<String, Object> response = new HashMap<>();

        User user = userService.loginUser(email, password);

        if (user != null) {
            response.put("success", true);
            response.put("message", "Login Successful!");
            response.put("userId", user.getId());
            response.put("username", user.getUsername());
            response.put("email", user.getEmail());
            response.put("genre", user.getFavoriteGenre());

            System.out.println("📤 LOGIN SUCCESS");
            System.out.println("UserId: " + user.getId());
        } else {
            response.put("success", false);
            response.put("message", "Invalid Credentials!");
            System.out.println("📤 LOGIN FAILED");
        }

        return response;
    }

   
    @PostMapping("/{userId}/watchlist/{movieId}")
    public Map<String, String> addToWatchlist(
            @PathVariable String userId,
            @PathVariable String movieId) {

        userService.addToWatchlist(userId, movieId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Added to Watchlist!");
        return response;
    }

    
    @DeleteMapping("/{userId}/watchlist/{movieId}")
    public Map<String, String> removeFromWatchlist(
            @PathVariable String userId,
            @PathVariable String movieId) {

        userService.removeFromWatchlist(userId, movieId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Removed from Watchlist!");
        return response;
    }

   
    @GetMapping("/{userId}")
    public User getUserProfile(
            @PathVariable String userId) {

        return userService.getUserById(userId);
    }
}