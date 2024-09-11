package com.example.demo.dto;

public class ErrorResponse {
	
	private String code;
	
	private String message;

	// Constructor
	public ErrorResponse(String code, String message) {
		this.code = code;
		this.message = message;
	}

	// Getters and setters
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	// To String Method
	@Override
	public String toString() {
		return "ErrorResponse [code=" + code + ", message=" + message + "]";
	}
	
	

}