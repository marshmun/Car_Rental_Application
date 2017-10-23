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
	public Car findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertCar(String year, String make, String model, String color) throws Exception {
		ResultSet rs = null;
		Connection conn = null;
		Statement st = null;

		try {
			//creating connection with DB
			conn = DBConnector.createConnection();
			

			// Prepare the SQL statmenet to insert the values
			PreparedStatement stmt = conn
					.prepareStatement("INSERT INTO cardetails(Year, Make, Model, Color)  VALUES (?,?,?,?)");
			stmt.setString(1, year);
			stmt.setString(2, make);
			stmt.setString(3, model);
			stmt.setString(4, color);

			// Execute the insert
			stmt.executeUpdate();
			conn.close();
			
			

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
	public void updateCar(int id, Car car) throws Exception {


		int rs;
		Connection conn = null;
		java.sql.PreparedStatement st = null;
	

		try {
			conn = DBConnector.createConnection();
			
		

			st = conn.prepareStatement("update cardetails SET Year = ?, Make = ?, Model = ?, Color= ?, Availability=?  where id = ?");
			st.clearParameters();
			st.setString(1, car.getYear() );
			st.setString(2, car.getMake() );
			st.setString(3, car.getModel());
			st.setString(4, car.getColor());
			st.setString(5, car.getAvailable());
			st.setInt(6, id);
			
			
			rs = st.executeUpdate();
			if (rs != 0) {
				
				
			} else {

			}
		} catch (Exception e) {
			//create new error object and push to the front.
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
	public void deleteCar(Car car) {
		// TODO Auto-generated method stub
		
	}

	

}
