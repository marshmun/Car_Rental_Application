package com.rental.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.rental.models.User;
import com.rental.work.DBConnector;


public class MySQLUserDAO implements UserDAO {

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByUserName(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByEmailAddress(String Email_Address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertUser(String fname, String lname, String email, String username, String pword) throws Exception {
		
		
		ResultSet rs = null;
		Connection conn = null;
		Statement st = null;

		try {
			//create connection with db
			conn = DBConnector.createConnection();
			

			// Prepare the SQL statmenet to insert the values
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO userdetails(First_Name, Last_Name, Email_Address, Password, User_Name)  VALUES (?,?,?,?,?)");
			stmt.setString(1, fname);
			stmt.setString(2, lname);
			stmt.setString(3, email);
			stmt.setString(4, pword);
			stmt.setString(5, username);

			// Execute the insert
			stmt.executeUpdate();
			conn.close();

			// Dispatch into success page
			
			
		} catch (Exception e) {
			throw e;
			
			
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (java.sql.SQLException e) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (java.sql.SQLException e) {
			}
			try {
				if (rs != null)
					rs.close();
			} catch (java.sql.SQLException e) {
			}
		}
	}

	@Override
	public void updateUser(int id, User user) throws Exception {
		int rs;
		Connection conn = null;
		java.sql.PreparedStatement st = null;
		

		try {
			//create a connection with the db
			conn = DBConnector.createConnection();
			
			
			st = conn.prepareStatement("update userdetails SET ID=?, First_Name =?, Last_Name=?, Email_Address=?,  User_Type = ?, Password = ?, User_Name=?, Car_Retnal=?  where id= ?");
			st.clearParameters();
			st.setInt(1, user.getId());
			st.setString(2, user.getFirstName());
			st.setString(3, user.getLastName());
			st.setString(4, user.getEmailAddress());
			st.setString(5, user.getType());
			st.setString(6, user.getPassword());
			st.setString(7, user.getUserName());
			st.setString(8, user.getCarRental());
			st.setInt(9, id);
			rs = st.executeUpdate();
				
			
		} catch (Exception e) {
			throw e;
			
			
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (java.sql.SQLException e) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (java.sql.SQLException e) {
			}

		}
	}

	@Override
	public boolean deleteEmployee(User user) {
		// TODO Auto-generated method stub
		return false;
	}


	
	

}
