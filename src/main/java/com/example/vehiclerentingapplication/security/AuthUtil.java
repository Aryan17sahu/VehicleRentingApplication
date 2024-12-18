package com.example.vehiclerentingapplication.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.vehiclerentingapplication.entity.User;
import com.example.vehiclerentingapplication.repository.UserRepository;

@Component
public class AuthUtil {

	private final UserRepository userRepository;

	public AuthUtil(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public String getCurrentUsername() {
		return getAuthentication().getName(); // We are accessing the username using this method
	}

	
	
	public User getCurrentUser() {
		
		  User user = userRepository.findByEmail(getCurrentUsername());
		  
		  if (user == null) {
				throw new UsernameNotFoundException("User not found with email: " + user);
			}
		  
		return user;
	}

}
