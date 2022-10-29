package com.pos.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pos.app.model.Food;

@Repository
public interface AdminRepository extends JpaRepository<Food, Integer>{
	
	public String findByName(String name);
	
	public List<Food> findByStatus(String status);
	
	

}
