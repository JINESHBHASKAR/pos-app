package com.pos.app.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pos.app.constants.AppConstants;
import com.pos.app.exception.BusinessException;
import com.pos.app.model.User;
import com.pos.app.model.UserDTO;
import com.pos.app.repository.UserRepository;
import com.pos.app.vo.StatusResponse;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	
	private Logger log = LoggerFactory.getLogger(JwtUserDetailsService.class);
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("inside loadUserByUsername()------ JwtUserDetails class");
		User user = userRepository.findByUsername(username);
		if (user == null) {
			log.error("User not found with username: "+ username);
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}
	
	public StatusResponse save(UserDTO user) {
		
		StatusResponse response = new StatusResponse();
		User newUser = new User();
		
		try {
			log.info("inside saveUser()------ JwtUserDetails class");
			
			newUser.setUsername(user.getUsername());
			newUser.setFirstName(user.getFirstName());
			newUser.setLastName(user.getLastName());
			newUser.setPhone(user.getPhone());
			newUser.setEmail(user.getEmail());
			newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
			
			if(userRepository.findByEmail(newUser.getEmail())!=null) {
				throw new BusinessException("Email already registered");
			}else if(userRepository.findByPhone(newUser.getPhone())!=null) {
				throw new BusinessException("Phone already registered");
			}else if(userRepository.findByUsername(newUser.getUsername())!=null) {
				throw new BusinessException("username already found");
			}
			
			newUser = userRepository.save(newUser);
			
			
			response.setStatus(AppConstants.STATUS_SUCCESS);
			response.setMessage("User Registration Successfull");
			response.setData(newUser);
			
		}catch(BusinessException e) {
			log.error("Error Message:"+ e.getMessage());
			throw new BusinessException(e.getMessage());
		}
		return response;
	}
}