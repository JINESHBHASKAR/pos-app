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

import com.pos.app.model.User;
import com.pos.app.model.UserDTO;
import com.pos.app.repository.UserRepository;

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
	
	public User save(UserDTO user) {
		log.info("inside saveUser()------ JwtUserDetails class");
		User newUser = new User();
		newUser.setUsername(user.getUsername());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPhone(user.getPhone());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userRepository.save(newUser);
	}
}