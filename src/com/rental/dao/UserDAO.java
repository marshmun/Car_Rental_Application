package com.rental.dao;

import java.sql.Connection;
import java.util.List;

import com.rental.models.User;

public interface UserDAO {

	 List<User> findAll();
	 User findById(String id);
	 User findByUserName(String username);
	 User findByUserName(String username, Connection conn);
	 User findByEmailAddress(String Email_Address);
	 
	 void insertUser (String fname, String lname, String email, String username, String pword) throws Exception;
	 void updateUser (int id, User user) throws Exception;
	 void updateUser (int id, User user, Connection conn) throws Exception;
	 boolean deleteEmployee(User user);
}
