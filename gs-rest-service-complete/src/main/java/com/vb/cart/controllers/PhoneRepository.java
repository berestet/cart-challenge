package com.vb.cart.controllers;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.vb.cart.model.Phone;

public interface PhoneRepository extends MongoRepository<Phone, String> {

    public Phone findById(String id);
    public List<Phone> findByName(String name);

}
