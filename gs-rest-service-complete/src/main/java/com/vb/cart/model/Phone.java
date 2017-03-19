package com.vb.cart.model;

public class Phone {

	private String id;
    private int age;
	private String imageUrl;
	private String name;
	private String snippet;
	private int quantity;

	public Phone(int age, String id, String imageUrl, String name, String snippet, int quantity) {
		this.id = id;
		this.age = age;
		this.imageUrl = imageUrl;
		this.name = name;
		this.snippet = snippet;
		this.quantity = quantity;
	}

	public Phone() {}
	
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSnippet() {
		return snippet;
	}

	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


}

