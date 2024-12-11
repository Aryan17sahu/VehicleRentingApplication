package com.example.vehiclerentingapplication.util;

public class ErrorStructure {
	
	public int status;
	public String message;
	public String rootCause;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getRootCause() {
		return rootCause;
	}
	public void setRootCause(String rootCause) {
		this.rootCause = rootCause;
	}
	
	public static ErrorStructure create(int status, String message, String rootCause) {
		ErrorStructure error  = new ErrorStructure();
		error.setStatus(status);
		error.setRootCause(rootCause);
		error.setMessage(message);
		
		return error;
	}
	

}
