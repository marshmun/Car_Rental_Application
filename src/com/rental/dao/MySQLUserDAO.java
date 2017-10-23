package com.rental.dao;

import java.sql.Connection;
import java.util.List;

import com.rental.models.User;
import com.rental.work.DBConnector;
import com.rental.work.ErrorHandling;

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
	public User findByUserName(String User_Name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByEmailAddress(String Email_Address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateUser(int id, User user) throws Exception {
		int rs;
		Connection conn = null;
		java.sql.PreparedStatement st = null;
		String nativeSQL = "";

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
