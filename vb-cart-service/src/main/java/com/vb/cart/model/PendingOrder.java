package com.vb.cart.model;

import java.util.List;

public class PendingOrder extends Order{
	
	public PendingOrder() {}
	
	public PendingOrder(String userId, List<Phone> phoneList) {
		super(userId, phoneList);
	}
	
}

