package com.example.demo.exception;

public class AmenityNotFoundException extends Exception {
	
private String message;
private String code;
	
	public AmenityNotFoundException(String code,String message) {
	this.code=code;
	this.message=message;
	}
	
	public String getMessage() {return this.message;}
	
	public String getCode() {
		return this.code;
	}
 
}
