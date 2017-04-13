package com.vb.cart.model;

import org.springframework.data.annotation.Id;


public class User {

    @Id
    private String userId;
    private String password;
	private String firstName;
	private String lastName;
	

	public User() {}

    public User(String userId, String password, String firstName, String lastName) {
    	super();
        this.userId = userId;
        this.password = password;
    	this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


    public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


    @Override
    public String toString() {
        return String.format(
                "User[userId=%s, password=%s, firstName='%s', lastName='%s']",
                userId, password, firstName, lastName);
    }

}

