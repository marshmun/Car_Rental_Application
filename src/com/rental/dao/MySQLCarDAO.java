package com.rental.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.rental.models.Car;
import com.rental.work.DBConnector;



public class MySQLCarDAO implements CarDAO{

	@Override
	public List<Car> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Car findById(String id) throws Exception {
		Connection conn = null;
		Statement st = null;
	   
	    try {
	    	st = conn.createStatement();
	    	  return findById(id, conn);
	    }catch(Exception e) {
	    	System.out.println(e);
	        throw e;
	     }finally {
				try { if (conn != null)	conn.close(); } catch (java.sql.SQLException e) {}
	     }
	}

	@Override
	public Car findById(String id, Connection conn)throws Exception {
	    ResultSet rs = null;
	    Car car = new Car();
		Statement st = null;
		
	    	try {
	    	rs = st.executeQuery("SELECT * FROM cardetails where id ='"+ id+"'");
	        if(rs.next()) {
	        car.setId(rs.getInt("id"));
	        car.setColor(rs.getString("Color"));
	        car.setMake(rs.getString("Make"));
	        car.setModel(rs.getString("Model"));
	        car.setYear(rs.getString("Year"));
	        car.setAvailable(rs.getString("Availability"));
	        }
	        return car;
	    	 }catch(Exception e) {
			    	System.out.println(e);
			        throw e;
			        
			    }
	   
	}
	
	@Override
	public void insertCar(Car car) throws Exception {
		Connection conn = null;                                                                                                                                                                                                                                                                                                                                                      

		try {
			conn = DBConnector.createConnection();
			
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO cardetails(Year, Make, Model, Color)  VALUES (?,?,?,?)");
			stmt.setString(1, car.getYear());
			stmt.setString(2, car.getMake());
			stmt.setString(3, car.getModel());
			stmt.setString(4, car.getColor());
			
			stmt.executeUpdate();
			conn.close();
			
			

		} catch (Exception e) {
		throw e;
			
			
		} finally {	try {if (conn != null)conn.close();} catch (java.sql.SQLException e) {}
			}
		
	}

	@Override
	public void updateCar(int id, Car car, Connection conn) throws Exception {
		java.sql.PreparedStatement st = null;
		
		st = conn.prepareStatement("update cardetails SET Year = ?, Make = ?, Model = ?, Color= ?, Availability=?  where id = ?");
		st.clearParameters();
		st.setString(1, car.getYear() );
		st.setString(2, car.getMake() );
		st.setString(3, car.getModel());
		st.setString(4, car.getColor());
		st.setString(5, car.getAvailable());
		st.setInt(6, id);
		
		
		st.executeUpdate();
		
	}	
	
	@Override
	public void updateCar(int id, Car car) throws Exception {
		
		Connection conn = null;
	
		try {
			conn = DBConnector.createConnection();
			
			updateCar(id, car, conn);

		} catch (Exception e) {
			throw e;
			
		} finally {
			try { if (conn != null) conn.close();} catch (java.sql.SQLException e) {}
		}
		
	}

	@Override
	public void deleteCar(String id,Car car, Connection conn)throws Exception {
		java.sql.PreparedStatement st = null;
		
		st = conn.prepareStatement("delete FROM cardetails where id= ?");
		st.clearParameters();
		st.setString(1, id);
		
		st.executeUpdate();
		
	}

	@Override
	public void deleteCar(String id, Car car) throws Exception {
		Connection conn = null;
		
		try {
			conn = DBConnector.createConnection();
			
			deleteCar(id, car, conn);

		} catch (Exception e) {
			throw e;
			
		} finally {
			try { if (conn != null) conn.close();} catch (java.sql.SQLException e) {}
		}
		
	}

	@Override
	public void deleteCar(int id, Car car) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCar(int id, Car car, Connection conn) throws Exception {
	java.sql.PreparedStatement st = null;
		
		st = conn.prepareStatement("delete FROM cardetails where id= ?");
		st.clearParameters();
		st.setInt(1, id);
		
		st.executeUpdate();
		
	}
		
	}


