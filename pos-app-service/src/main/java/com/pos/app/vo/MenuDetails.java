package com.pos.app.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.pos.app.model.Food;

public class MenuDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<String> top_offers;

	private List<Food> popular_foods;

	private List<Food> recent_foods;

	public MenuDetails() {

	}

	public MenuDetails(List<String> top_offers, List<Food> popular_foods, List<Food> recent_foods) {
		super();
		this.top_offers = top_offers;
		this.popular_foods = popular_foods;
		this.recent_foods = recent_foods;
	}

	public List<String> getTop_offers() {
		return top_offers;
	}

	public void setTop_offers(List<String> top_offers) {
		this.top_offers = top_offers;
	}

	public List<Food> getPopular_foods() {
		return popular_foods;
	}

	public void setPopular_foods(List<Food> popular_foods) {
		this.popular_foods = popular_foods;
	}

	public List<Food> getRecent_foods() {
		return recent_foods;
	}

	public void setRecent_foods(List<Food> recent_foods) {
		this.recent_foods = recent_foods;
	}

}
