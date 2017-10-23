package com.rental.dao;

import java.util.List;

import com.rental.models.User;

public interface UserDAO {

	 List<User> findAll();
	 User findById(String id);
	 User findByUserName(Object object);
	 User findByEmailAddress(String Email_Address);
	 
	 void insertUser (String fname, String lname, String email, String username, String pword) throws Exception;
	 void updateUser (int id, User user) throws Exception;
	 boolean deleteEmployee(User user);
}
