package com.example.vehiclerentingapplication.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.vehiclerentingapplication.entity.Image;
import com.example.vehiclerentingapplication.entity.User;
import com.example.vehiclerentingapplication.exceptions.FailedToUploadImageException;
import com.example.vehiclerentingapplication.exceptions.ImageNotFoundByIdException;
import com.example.vehiclerentingapplication.exceptions.UserNotFoundByIdException;
import com.example.vehiclerentingapplication.repository.ImageRepository;
import com.example.vehiclerentingapplication.repository.UserRepository;

 

@Service
public class ImageService {
	

	private final UserRepository userRepository;
	private final ImageRepository imageRepository;
	
	public ImageService(UserRepository userRepository, ImageRepository imageRepository) {
		this.userRepository = userRepository;
		this.imageRepository = imageRepository;
	}

	public void addUserProfilePicture(int userId, MultipartFile file) {
		Optional<User> optional = userRepository.findById(userId);
		if (optional.isPresent()) {
			User user = optional.get();
			
			if (user.getImage()!=null) {
				Image image = user.getImage();
				this.uploadProfilePhoto(file, user);
				imageRepository.delete(image);
			}
			
			this.uploadProfilePhoto(file, user);
		} else {
			throw new UserNotFoundByIdException("User Not Found By Given Id");
		}
	}
	
	public void uploadProfilePhoto(MultipartFile file, User user) {
		Image image = imageRepository.save(this.getImage(file));
		user.setImage(image);
		userRepository.save(user);
	}

	public Image getImage(MultipartFile file) {
		Image image = new Image();
		try {
			byte[] imageBytes = file.getBytes();
			image.setContentType(file.getContentType());
			image.setImageBytes(imageBytes);
		} catch (IOException e) {
			throw new FailedToUploadImageException("Failed to upload the image");
		}
		return image;
	}

	public Image findImageById(int imageId) {
	
		Optional<Image> optional = imageRepository.findById(imageId);
		if (optional.isPresent()) {
		 return optional.get();
		} else {
	     throw new ImageNotFoundByIdException("Image Not Found By Id");
		}

	}

}
