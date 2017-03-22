package com.vb.cart.controllers;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vb.cart.model.PendingOrder;

@Repository("pendingOrders")
public interface PendingOrderRepository extends MongoRepository<PendingOrder, String> {

    public PendingOrder findByOrderId(String orderId);
    public PendingOrder findByUserId(String userId);

}
