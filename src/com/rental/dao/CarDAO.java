package com.rental.dao;

import java.sql.Connection;
import java.util.List;

import com.rental.models.Car;

public interface CarDAO {
	
	List<Car> findAll();
	Car findById(String id);
	Car findById(String id, Connection conn);
	
	
	
	void insertCar(Car car) throws Exception;
	void updateCar(int id, Car car) throws Exception;
	void updateCar(int id, Car car, Connection conn) throws Exception;
	void deleteCar(int id,Car car)throws Exception;
	void deleteCar(String id, Car car) throws Exception;
	void deleteCar(String id,Car car, Connection conn)throws Exception;
	void deleteCar(int id,Car car, Connection conn)throws Exception;

}
