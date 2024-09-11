package com.example.demo.exception;

public class PaymentNotFoundException extends Exception {
	
private String code;
private String message;
	
	public PaymentNotFoundException(String code,String message) {
		this.message = message;
		this.code=code;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public String getCode() {
		return this.code;
	}

}
