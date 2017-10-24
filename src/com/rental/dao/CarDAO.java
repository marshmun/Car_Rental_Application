package com.rental.dao;

import java.sql.Connection;
import java.util.List;

import com.rental.models.Car;

public interface CarDAO {
	
	List<Car> findAll();
	Car findById(String id);
	Car findById(String id, Connection conn);
	
	
	
	void insertCar(String year, String make, String model, String color) throws Exception;
	void updateCar(int id, Car car) throws Exception;
	void updateCar(int id, Car car, Connection conn) throws Exception;
	void deleteCar(Car car);

}
