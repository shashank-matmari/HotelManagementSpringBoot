package com.example.demo.exception;

public class ReviewNotFoundException extends Exception{
	private String message;
	private String code;
	//private String message;
	
	public ReviewNotFoundException(String code, String message){
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
