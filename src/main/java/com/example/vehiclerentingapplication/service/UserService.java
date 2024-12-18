package com.example.vehiclerentingapplication.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

 import com.example.vehiclerentingapplication.entity.Image;
import com.example.vehiclerentingapplication.entity.User;
import com.example.vehiclerentingapplication.enums.UserRole;
import com.example.vehiclerentingapplication.exceptions.UserNotFoundByIdException;
import com.example.vehiclerentingapplication.mapper.UserMapper;
import com.example.vehiclerentingapplication.repository.UserRepository;
import com.example.vehiclerentingapplication.requestedto.UserRequest;
import com.example.vehiclerentingapplication.respondedto.UserResponse;
import com.example.vehiclerentingapplication.security.AuthUtil;

import jakarta.security.auth.message.config.AuthConfig;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	private final AuthUtil authUtil;

	 
	public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder,
			AuthUtil authUtil) {
		super();
		this.userRepository = userRepository;
		this.userMapper = userMapper;
		this.passwordEncoder = passwordEncoder;
		this.authUtil =  authUtil;
	}

	public UserResponse register(UserRequest userRequest, UserRole role) {
		User user = userMapper.mapToUser(userRequest, new User());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(role);
		user = userRepository.save(user);
		return userMapper.mapToUserResponse(user);

	}

	public UserResponse getUserById() {
//		User user = userRepository.findById(userId)
//				.orElseThrow(() -> new UserNotFoundByIdException("User not found by given id"));
		User user = authUtil.getCurrentUser();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		UserResponse response = userMapper.mapToUserResponse(user);
		this.setProfilePictureURL(response, user.getImage());
		return response;
	}

	private void setProfilePictureURL(UserResponse userResponse, Image profilePicture) {
		if (profilePicture != null) {
			userResponse.setProfilePictureLink("/find-image-by-id?imageId=" + profilePicture.getImageId());
		}
	}

	public UserResponse updateUser(UserRequest userRequest) {
//		Optional<User> optional = userRepository.findById(userId);
//		if (optional.isPresent()) {
//			User exUser = optional.get();
			User user = authUtil.getCurrentUser();
			User updatedUser = userMapper.mapToUser(userRequest, user);
			User savedUser = userRepository.save(updatedUser);
			UserResponse response = userMapper.mapToUserResponse(savedUser);
			this.setProfilePictureURL(response, user.getImage());
			return response;
//		} else {
//			throw new UserNotFoundByIdException("User  not found by given id");
//		}
	}
}