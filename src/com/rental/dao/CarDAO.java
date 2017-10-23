package com.rental.dao;

import java.util.List;

import com.rental.models.Car;

public interface CarDAO {
	
	List<Car> findAll();
	Car findById(String id);
	
	
	
	void insertCar(String year, String make, String model, String color) throws Exception;
	void updateCar(int id, Car car) throws Exception;
	void deleteCar(Car car);

}
