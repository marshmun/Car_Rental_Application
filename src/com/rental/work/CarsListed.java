package com.rental.work;
import com.rental.models.Car;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarsListed {
	
	
	
	public List<Car> createcarlist() throws ClassNotFoundException, SQLException {
		
		ResultSet result  =null;
		Statement sp = null;
		Connection conn = null;
		ResultSet rs = null;
		List <Car> cars = new ArrayList<>();
		
		
		try {
 			conn = DBConnector.createConnection();
			sp = conn.createStatement();
			conn.setAutoCommit(false);
			result = sp.executeQuery("SELECT * FROM cardetails");
			while (result.next()) {
				Car car = new Car();
				car.setId(result.getInt("id"));
				car.setYear(result.getString("Year"));
				car.setMake(result.getString("Make"));
				car.setModel(result.getString("Model"));
				car.setColor(result.getString("Color"));
				car.setAvailable(result.getString("Availability"));
				cars.add(car);
			}
			
			
			
		}catch(Exception e) {
			
		}finally{
			try {
				if (sp != null)
					sp.close();
			} catch (java.sql.SQLException e) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (java.sql.SQLException e) {
			}
			
		}
		return cars;

	}
	
	
}
