package com.example.vehiclerentingapplication.exceptions;

 
public class ImageNotFoundByIdException extends BaseException {
	private static final long serialVersionUID = 1L;

	public ImageNotFoundByIdException(String message) {
		super(message);
	}

}