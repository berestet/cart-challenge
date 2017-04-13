package com.vb.cart.model;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Order {


	@Id
	private String orderId;	
	private String userId;
    private List<Phone> phoneList;

	
	public Order() {}
	
	public Order(String userId, List<Phone> phoneList) {
		this.userId = userId;
		this.phoneList = phoneList;
	}
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<Phone> getPhoneList() {
		return phoneList;
	}

	public void setPhone(List<Phone> phoneList) {
		this.phoneList = phoneList;
	}


}

