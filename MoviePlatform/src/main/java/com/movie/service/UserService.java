package com.movie.service;

import com.movie.models.User;
import com.movie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    
    
    public String registerUser(User user) {
        System.out.println("\n========== REGISTER USER ==========");
        System.out.println("Username    : " + user.getUsername());
        System.out.println("Email       : " + user.getEmail());
        System.out.println("Password    : " + user.getPassword());
        System.out.println("Genre       : " + user.getFavoriteGenre());

       
        
        boolean emailExists = userRepository
                .existsByEmail(user.getEmail());

        if (emailExists) {
            System.out.println("❌ Email already registered!");
            return "Email already registered!";
        }

       
        
        if (user.getWatchlist() == null) {
            user.setWatchlist(new ArrayList<>());
        }

        try {
            // Save user to MongoDB
            User savedUser = userRepository.save(user);

            System.out.println("✅ User Saved Successfully!");
            System.out.println("Saved ID    : " + savedUser.getId());
            System.out.println("==================================\n");

            return "success";

        } catch (Exception e) {
            System.out.println("❌ Save Error: " + e.getMessage());
            e.printStackTrace();
            return "Error saving user: " + e.getMessage();
        }
    }

    
    
    public User loginUser(String email, String password) {
        System.out.println("\n========== LOGIN USER ==========");
        System.out.println("Email       : " + email);
        System.out.println("Password    : " + password);

        try {

            User user = userRepository.findByEmail(email);

            System.out.println("User Found  : " + (user != null));

            if (user == null) {
                System.out.println("❌ Email not found in database!");
                System.out.println("==============================\n");
                return null;
            }

            System.out.println("DB Password : " + user.getPassword());
            System.out.println("Input Pass  : " + password);

            
            if (user.getPassword().equals(password)) {
                System.out.println(" Password Matches!");
                System.out.println("==============================\n");
                return user;
            } else {
                System.out.println("❌ Password Does Not Match!");
                System.out.println("==============================\n");
                return null;
            }

        } catch (Exception e) {
            System.out.println("❌ Login Error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


    
    public void addToWatchlist(String userId, String movieId) {
        try {
            User user = userRepository
                    .findById(userId).orElse(null);

            if (user == null) return;

            if (!user.getWatchlist().contains(movieId)) {
                user.getWatchlist().add(movieId);
                userRepository.save(user);
                System.out.println("✅ Added to watchlist: " + movieId);
            }

        } catch (Exception e) {
            System.out.println("❌ Watchlist Error: " + e.getMessage());
        }
    }

    
    
    public void removeFromWatchlist(String userId, String movieId) {
        try {
            User user = userRepository
                    .findById(userId).orElse(null);

            if (user == null) return;

            user.getWatchlist().remove(movieId);
            userRepository.save(user);
            System.out.println("✅ Removed from watchlist: " + movieId);

        } catch (Exception e) {
            System.out.println("❌ Remove Error: " + e.getMessage());
        }
    }

   
    
    
    public User getUserById(String userId) {
        try {
            return userRepository
                    .findById(userId).orElse(null);
        } catch (Exception e) {
            System.out.println("❌ Get User Error: " + e.getMessage());
            return null;
        }
    }
}