package com.pos.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pos.app.dto.FoodDTO;
import com.pos.app.model.Food;
import com.pos.app.service.AdminService;
import com.pos.app.vo.StatusResponse;

@RestController
public class AdminController {
	
	Logger logger =LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/add-menu")
	public ResponseEntity<?> addMenu(@RequestBody FoodDTO food){
		
		logger.info("inside addMenu() in AdminController");
		
		Food menu = adminService.addMenu(food);
		
		StatusResponse response = new StatusResponse();
		response.setData(menu);
		response.setMessage("New Food Added");
		response.setStatus(true);
		
		return ResponseEntity.ok(response);
	}

}
