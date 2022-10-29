package com.pos.app.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos.app.dto.FoodDTO;
import com.pos.app.exception.BusinessException;
import com.pos.app.model.Food;
import com.pos.app.repository.AdminRepository;
import com.pos.app.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{

	
	Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
	
	@Autowired
	private  AdminRepository adminRepository;
	
	@Override
	public Food addMenu(FoodDTO food) {
		
		logger.info("inside addMenu()  in AdminServiceImpl");
		
		Food newMenu = new Food();
		try {
			
			logger.info("before condition check --- AdminServiceImp");
			if(adminRepository.findByName(food.getName())!=null) {
				logger.info("food already found");
				throw new BusinessException("This food already added");
			}
			
			newMenu.setCategory(food.getCategory());
			newMenu.setName(food.getName());
			newMenu.setPrice(food.getPrice());
			newMenu.setRating(food.getRating());
			newMenu.setStatus(food.getStatus());
			newMenu.setImage(food.getImage());
			
			logger.info("before save() check --- AdminServiceImp");
			newMenu = adminRepository.save(newMenu);
			
			
		} catch (BusinessException e) {
			logger.error("ERROR:"+ e.getMessage());
			throw new BusinessException(e.getMessage());
		}
		
		return newMenu;
	}
	
	

}
