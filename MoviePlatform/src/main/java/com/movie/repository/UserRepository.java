package com.movie.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.movie.models.User;

@Repository
public interface UserRepository
        extends MongoRepository<User, String> {

   
    User findByEmail(String email);

 
    User findByEmailAndPassword(String email, String password);

   
    boolean existsByEmail(String email);
}