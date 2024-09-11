package com.example.demo.exception;

public class LocationNotFoundException extends Exception{
	
	private String code;
	private String message;
	
	public LocationNotFoundException(String code, String message){
		this.code = code;
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public String getCode() {
		return this.code;
	}

}
