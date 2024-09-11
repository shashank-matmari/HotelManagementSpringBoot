package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.SuccessResponse;
import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	@Autowired
	UserService userService;
	
	// API to create a new user
	@PostMapping("/post")
	public ResponseEntity<SuccessResponse> createUser(@Valid @RequestBody User user) throws UserNotFoundException{
		return new ResponseEntity<SuccessResponse>(userService.createUser(user), HttpStatus.CREATED);
	}
	
	// API to get all users
	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllUsers() throws UserNotFoundException{
		return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
	}
	
	// API to get user details by user id
	@GetMapping("/{user_id}")	
	public ResponseEntity<User> getUserByID(@PathVariable("user_id")int userId) throws UserNotFoundException{
		return new ResponseEntity<User>(userService.getUserById(userId), HttpStatus.OK);
	}
	
	// API to update user
	@PutMapping("/update/{user_id}")
	public ResponseEntity<SuccessResponse> updateUser(@Valid @RequestBody User user, @PathVariable("user_id")int userId) throws UserNotFoundException{
		User updateUser = userService.getUserById(userId);
		updateUser.setUser_name(user.getUser_name());
		updateUser.setPassword(user.getPassword());
		updateUser.setEmail(user.getEmail());
		updateUser.setRole(user.getRole());
		return new ResponseEntity<SuccessResponse>(userService.updateUser(updateUser), HttpStatus.OK);
	}
	
	// API to delete a user
	@DeleteMapping("/delete/{user_id}")
	public ResponseEntity<SuccessResponse> deleteUserById(@PathVariable("user_id")int userId) throws UserNotFoundException{
		return new ResponseEntity<SuccessResponse>(userService.deleteUserById(userId), HttpStatus.OK);
	}
	
	// API to validate login details
	@GetMapping("/login/{user_name}/{password}")
	public ResponseEntity<User> validateLogin(@PathVariable("user_name")String user_name, @PathVariable("password") String password) throws UserNotFoundException{
		return new ResponseEntity<User>(userService.validateUser(user_name, password), HttpStatus.OK);
	}
	
	// API to validate email
	@GetMapping("/validate/{email}")
	public ResponseEntity<Boolean> vaildateEmail(@PathVariable("email") String email){
		return new ResponseEntity<Boolean>(userService.vaildateEmail(email),HttpStatus.OK);
	}
	
	// API to validate user name
	@GetMapping("/validate/{user_name}")
	public ResponseEntity<Boolean> vaildateUserName(@PathVariable("user_name") String user_name){
		return new ResponseEntity<Boolean>(userService.vaildateEmail(user_name),HttpStatus.OK);
	}

}
