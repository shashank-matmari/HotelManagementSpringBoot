package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	// Custom query to validate user details 
	@Query("select u from User u where user_name=:user_name and password=:password")
	public User validateUser(@Param("user_name") String user_name,@Param("password") String password);
	
	// Custom query to find user details by email
	@Query("select u from User u where email=:email")
	public User validateEmail(@Param("email") String emaail);
	
	// Custom query to find user details by user name 
	@Query("select u from User u where user_name=:user_name")
	public User validateUserName(@Param("user_name") String user_name);
	
}
