package com.example.vehiclerentingapplication.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.vehiclerentingapplication.entity.Image;
import com.example.vehiclerentingapplication.service.ImageService;
import com.example.vehiclerentingapplication.util.SimpleResponseStructure;


@RestController
public class ImageController {
	
	private final ImageService imageService;

	public ImageController(ImageService imageService) {
		super();
		this.imageService = imageService;
	}

	@PostMapping("/add-user-profile-picture")
	public ResponseEntity<SimpleResponseStructure> addUserProfilePicture
	( @RequestParam("file") MultipartFile file ){
		imageService.addUserProfilePicture( file);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(SimpleResponseStructure.create(HttpStatus.CREATED.value(),"User-Profile-Updated"));
	}

	@GetMapping("/find-image-by-id")
	public ResponseEntity<byte []> findImageById (@RequestParam("imageId") int imageId){
		Image image = imageService.findImageById(imageId);
		return ResponseEntity
				.status(HttpStatus.FOUND)
				.contentType(MediaType.valueOf(image.getContentType()))
				.body(image.getImageBytes());
	}

}
