package com.vb.cart.controllers;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.vb.cart.model.User;

public interface UserRepository extends MongoRepository<User, String> {

    public User findByUserId(String userId);

}
