package com.rental.dao;

import java.util.List;

import com.rental.models.User;

public interface UserDao {

	 List<User> findAll();
	 List<User> findById();
	 List<User> findByUserName();
	 List<User> findByEmailAddress();
	 
	 boolean insertUser (User user);
	 boolean updateUser (User user);
	 boolean deleteEmployee(User user);
}
