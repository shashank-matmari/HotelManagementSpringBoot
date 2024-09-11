package com.example.demo.dto;

public class LoginResponse {
	
	private boolean isLogin;

	// Constructor with parameters
	public LoginResponse(boolean isLogin) {
		super();
		this.isLogin = isLogin;
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

	// To String method
	@Override
	public String toString() {
		return "LoginResponse :" + isLogin;
	}
	
}
