package com.pos.app.service;

import com.pos.app.dto.FoodDTO;
import com.pos.app.model.Food;

public interface AdminService {
	
	public Food addMenu(FoodDTO food);

}
