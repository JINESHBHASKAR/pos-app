package com.pos.app.dto;

import java.io.Serializable;

public class FoodDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private String category;

	private String rating;

	private String price;

	private String image;

	private String status;
	
	public FoodDTO() {
		// TODO Auto-generated constructor stub
	}
	
	

	public FoodDTO(String name, String category, String rating, String price, String image, String status) {
		super();
		this.name = name;
		this.category = category;
		this.rating = rating;
		this.price = price;
		this.image = image;
		this.status = status;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
