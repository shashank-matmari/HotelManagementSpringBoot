package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.SuccessResponse;
import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;

public interface UserService {

	SuccessResponse createUser(User user) throws UserNotFoundException;
	
	List<User> getAllUsers() throws UserNotFoundException;
	
	User getUserById(int user_id) throws UserNotFoundException;
	
	SuccessResponse updateUser(User user) throws UserNotFoundException;
	
	SuccessResponse deleteUserById(int user_id) throws UserNotFoundException;

	User validateUser(String user_name, String password);
	
	boolean vaildateEmail(String email);
	
	boolean vaildateUserName(String user_name);
	
}
