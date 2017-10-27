package com.rental.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	public User findByUserName(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByUserName(String username, Connection conn) throws SQLException {

			Statement st =null;
		    ResultSet rs =null;
		    User user = new User();
		    try {
		    	st=conn.createStatement();
		    	
		    	rs = st.executeQuery("SELECT * FROM userdetails where User_Name ='"+ username+"'");
		        if(rs.next()) {
		        user.setId(rs.getInt("ID"));
		        user.setUserName(rs.getString("User_Name"));
		        user.setFirstName(rs.getString("First_Name"));
		        user.setLastName(rs.getString("Last_Name"));
		        user.setEmailAddress(rs.getString("Email_Address"));
		        user.setPassword(rs.getString("password"));
		        user.setCarRental(rs.getString("Car_Rental"));
		        user.setType(rs.getString("USer_Type"));
		        }
		        
		        
		        return user;
		    }catch(Exception e) {
		    	System.out.println(e);
		        throw e;
		        
		    }


		}
	
	@Override
	public User findByEmailAddress(String Email_Address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertUser(User user) throws Exception {
		
		
		ResultSet rs = null;
		Connection conn = null;
		Statement st = null;

		try {
			//create connection with db
			conn = DBConnector.createConnection();
			

			// Prepare the SQL statmenet to insert the values
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO userdetails(First_Name, Last_Name, Email_Address, Password, User_Name)  VALUES (?,?,?,?,?)");
			stmt.setString(1, user.getFirstName());
			stmt.setString(2, user.getLastName());
			stmt.setString(3, user.getEmailAddress());
			stmt.setString(4, user.getPassword());
			stmt.setString(5, user.getUserName());

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

	public void updateUser(int id, User user, Connection conn) throws Exception {
		java.sql.PreparedStatement st = null;

		st = conn.prepareStatement("update userdetails SET First_Name =?, Last_Name=?, Email_Address=?,  User_Type = ?, Password = ?, User_Name=?, Car_Rental=?  where id= ?");
		st.clearParameters();
		//st.setInt(1, user.getId());
		st.setString(1, user.getFirstName());
		st.setString(2, user.getLastName());
		st.setString(3, user.getEmailAddress());
		st.setString(4, user.getType());
		st.setString(5, user.getPassword());
		st.setString(6, user.getUserName());
		st.setString(7, user.getCarRental());
		st.setInt(8, id);
		st.executeUpdate();
		
	}
	
	
	@Override
	public void updateUser(int id, User user) throws Exception {		
		Connection conn = null;
		
		try {
			//create a connection with the db
			conn = DBConnector.createConnection();
	
			updateUser(id, user, conn);
										
		} catch (Exception e) {
			throw e;
		} finally {
			try { if (conn != null)	conn.close(); } catch (java.sql.SQLException e) {}
		}
	}


	@Override
	public User findByCarRental(String carid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUser(String car, User user, Connection conn) throws Exception {
		java.sql.PreparedStatement st = null;

		st = conn.prepareStatement("update userdetails SET ID =?, First_Name =?, Last_Name=?, Email_Address=?,  User_Type = ?, Password = ?, User_Name=?, Car_Rental=?  where Car_Rental= ?");
		st.clearParameters();
		st.setInt(1, user.getId());
		st.setString(2, user.getFirstName());
		st.setString(3, user.getLastName());
		st.setString(4, user.getEmailAddress());
		st.setString(5, user.getType());
		st.setString(6, user.getPassword());
		st.setString(7, user.getUserName());
		st.setString(8, user.getCarRental());
		st.setString(9, car);
		st.executeUpdate();
		
	}

	@Override
	public User findByCarRental(String carid, Connection conn)throws Exception {
		Statement st =null;
	    ResultSet rs =null;
	    User user = new User();
	    try {
	    	st=conn.createStatement();
	    	
	    	rs = st.executeQuery("SELECT * FROM userdetails where Car_Rental ='"+ carid+"'");
	        if(rs.next()) {
	        user.setId(rs.getInt("ID"));
	        user.setUserName(rs.getString("User_Name"));
	        user.setFirstName(rs.getString("First_Name"));
	        user.setLastName(rs.getString("Last_Name"));
	        user.setEmailAddress(rs.getString("Email_Address"));
	        user.setPassword(rs.getString("password"));
	        }else {user=null;}
	        
	        
	        return user;
	    }catch(Exception e) {
	    	System.out.println(e);
	        throw e;
	        
	    }

	}

	@Override
	public void deleteUser(String id,User user) throws Exception {
	Connection conn = null;
		
		try {
			//create a connection with the db
			conn = DBConnector.createConnection();
	
			deleteUser(id, user, conn);
										
		} catch (Exception e) {
			throw e;
		} finally {
			try { if (conn != null)	conn.close(); } catch (java.sql.SQLException e) {}
		}
	}
		
	

	@Override
	public void deleteUser(String car, User user, Connection conn) throws Exception {
		java.sql.PreparedStatement st = null;
		
		st = conn.prepareStatement("delete FROM userdetails where User_Name= ?");
		st.clearParameters();
		st.setString(1, car);
		
		st.executeUpdate();
		
	}
		
	}


	
	


