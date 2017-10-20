package com.rental.dao;

import java.util.List;

import com.rental.models.Car;

public interface CarDao {
	
	List<Car> findAll();
	List<Car> findById();
	
	
	
	boolean insertCar(Car car);
	boolean updateCar(Car car);
	boolean deleteCar(Car car);

}
