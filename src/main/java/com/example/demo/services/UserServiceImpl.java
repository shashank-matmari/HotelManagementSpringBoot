package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.SuccessResponse;
import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;

	// Method to create a user
	@Override
	public SuccessResponse createUser(User user) throws UserNotFoundException {
		// TODO Auto-generated method stub
		if(userRepository.findById(user.getUser_id()).isEmpty()) {
			userRepository.save(user);
			return new SuccessResponse("POSTSUCCESS", "User added successfully");
		}
		else
			throw new UserNotFoundException("ADDFAILS", "User already exists");
	}

	// Method to get all users
	@Override
	public List<User> getAllUsers() throws UserNotFoundException {
		// TODO Auto-generated method stub
		if(userRepository.findAll().isEmpty())
			throw new UserNotFoundException("GETALLFAILS", "User list is empty");
		else 
			return userRepository.findAll();
	}

	// Method to find user by id
	@Override
	public User getUserById(int user_id) throws UserNotFoundException {
		// TODO Auto-generated method stub
		if(userRepository.findById(user_id).isEmpty()) {
			throw new UserNotFoundException("GETFAILS", "User doesn't exists");
		}
		else
			return userRepository.findById(user_id).get();
	}

	// Method to update a user
	@Override
	public SuccessResponse updateUser(User user) throws UserNotFoundException {
		// TODO Auto-generated method stub
		if(userRepository.findById(user.getUser_id()).isEmpty()) {
			throw new UserNotFoundException("UPDATEFAILS", "User doesn't exists");
		}
		else {
			userRepository.save(user);
			return new SuccessResponse("UPDATESUCCESS", "User updated successfully");
		}
	}

	// Method to delete a user by id
	@Override
	public SuccessResponse deleteUserById(int user_id) throws UserNotFoundException {
		// TODO Auto-generated method stub
		if(userRepository.findById(user_id).isEmpty()) {
			throw new UserNotFoundException("DLTFAILS", "User doesn't exists");
		}
		else {
			userRepository.deleteById(user_id);
			return new SuccessResponse("DELETESUCCESS", "User deleted successfully");
		}
	}

	// Method to validate a user
	@Override
	public User validateUser(String user_name, String password) {
		return userRepository.validateUser(user_name, password);
		
	}
	
	// Method to validate email of the user
	@Override
	public boolean vaildateEmail(String email) {
		User currentEmail=userRepository.validateEmail(email);
		if(currentEmail!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	// Method to validate user name of the user
	@Override
	public boolean vaildateUserName(String user_name) {
		User currentUserName=userRepository.validateUserName(user_name);
		if(currentUserName!=null) {
			return true;
		}else {
			return false;
		}
	}
	

}
