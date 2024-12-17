package com.example.vehiclerentingapplication.exceptions;

 
public class FailedToUploadImageException extends BaseException {
	private static final long serialVersionUID = 1L;

	public FailedToUploadImageException(String message) {
		super(message);
	}

}