package com.example.demo.dto;

public class LoginDTO {
	private String user_name;
	private String password;
	
	// Default Constructor
	public LoginDTO() {
		
	}

	// Constructor with parameters
	public LoginDTO(String user_name, String password) {
		super();
		this.user_name = user_name;
		this.password = password;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
