package com.rental.dao;

import java.sql.Connection;
import java.util.List;

import com.rental.models.User;

public interface UserDAO {

	 List<User> findAll();
	 User findById(String id);
	 User findByUserName(String username) throws Exception;
	 User findByUserName(String username, Connection conn) throws Exception;
	 User findByEmailAddress(String Email_Address);
	 User findByCarRental(String carid);
	 User findByCarRental(String carid,Connection conn)throws Exception;
	 
	 void insertUser (User user) throws Exception;
	 void updateUser (int id, User user) throws Exception;
	 void updateUser (int id, User user, Connection conn) throws Exception;
	 void updateUser (String car, User user, Connection conn) throws Exception;
	 void deleteUser(String id,User user)throws Exception;
	 void deleteUser(String car,User user, Connection conn)throws Exception;
}
