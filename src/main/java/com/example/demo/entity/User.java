package com.example.demo.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class User {
	
	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull(message = "User Id should not be null")
	private int user_id;
	
	@NotEmpty(message = "User name should not be empty")
	@NotNull(message = "User name should not be null")
	@Size(min = 6, message = "User name should contain minimum of 6 letters")
	@Column(name = "USER_NAME", unique = true)
	private String user_name;
	
	@Pattern(regexp = "([0-9]*)(?=.*[a-z])(?=.*[A-Z])(?=.*[@$*#]).{6,12}", 
			message = "Password should contain one Capital Letter, one Small Letter, a special character(@$*#)")
	@Column(name = "PASSWORD")
	private String password;
	
	@Pattern(regexp = "^[a-z0-9]{4,}@email.com$")
	@Column(name = "EMAIL", unique = true)
	private String email;
	
	@Column(name = "ROLE")
	private String role;

	// Default constructor
	public User() {
		super();
	}

	// Constructor with parameters
	public User(
			@NotNull(message = "User Id should not be null") @NotEmpty(message = "User Id sould not be empty") int user_id,
			@NotEmpty(message = "User name should not be empty") @NotNull(message = "User name should not be null") @Size(min = 6, message = "User name should contain minimum of 6 letters") String user_name,
			@Pattern(regexp = "([0-9]*)(?=.*[a-z])(?=.*[A-Z])(?=.*[@$*#]).{6,12}", message = "Password should contain one Capital Letter, one Small Letter, a special character(@$*#)") String password,
			@Pattern(regexp = "^[a-z0-9]{4,}@email.com$") String email, String role) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.password = password;
		this.email = email;
		this.role = role;
	}

	// Getters and setters
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	// To String method
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_name=" + user_name + ", password=" + password + ", email=" + email
				+ ", role=" + role + "]";
	}
	
	

}
