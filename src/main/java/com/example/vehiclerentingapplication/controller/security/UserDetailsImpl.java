package com.example.vehiclerentingapplication.controller.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.vehiclerentingapplication.entity.User;

public class UserDetailsImpl implements UserDetails{

	private static final long serialVersionUID = 1L;

	private final User user;
	
	public UserDetailsImpl(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

}
