package com.rental.dao;

import java.util.List;

import com.rental.models.Car;

public interface CarDAO {
	
	List<Car> findAll();
	List<Car> findById(String id);
	
	
	
	boolean insertCar(Car car);
	void updateCar(int id, Car car) throws Exception;
	boolean deleteCar(Car car);

}
