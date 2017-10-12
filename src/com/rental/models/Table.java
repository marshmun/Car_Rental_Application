package com.rental.models;
import com.rental.models.CarData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Table {
	
	
	public List<Car> getTableConnection() throws ClassNotFoundException, SQLException {
		List <Car> cars = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/carrentalsystem";
			String username = "root";
			String password = "javatest";
			String query = "select * from cardetails";
			Connection conn = DriverManager.getConnection(url, username, password);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Car car = new Car();
				car.setId(rs.getInt("id"));
				car.setYear(rs.getString("Year"));
				car.setMake(rs.getString("Make"));
				car.setModel(rs.getString("Model"));
				car.setColor(rs.getString("Color"));
				car.setAvailable(rs.getString("Availability"));
				cars.add(car);
			}
			
			
			
		}catch(Exception e) {
			
		}finally{
			
		}
		return cars;

	}
	
	
}
