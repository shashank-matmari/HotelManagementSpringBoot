package com.example.demo.dto;

public class SuccessResponse {
	
	private String code;
	
	private String message;

	// Constructor with parameters
	public SuccessResponse(String code, String message) {
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

	// To String method
	@Override
	public String toString() {
		return "ErrorResponse [code=" + code + ", message=" + message + "]";
	}
	
	

}
