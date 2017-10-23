package com.rental.dao;

import java.util.List;

import com.rental.models.User;

public interface UserDAO {

	 List<User> findAll();
	 User findById(String id);
	 User findByUserName(String User_Name);
	 User findByEmailAddress(String Email_Address);
	 
	 boolean insertUser (User user);
	 void updateUser (int id, User user) throws Exception;
	 boolean deleteEmployee(User user);
}
