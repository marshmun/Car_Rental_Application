package com.rental.models;

public class Car {

	private String Id;
	private String Year;
	private String Make;
	private String Model;
	private String Color;
	private String Available;
	
	public Car() {
		super();
	}

	public String getYear() {
		return Year;
	}

	public void setYear(String year) {
		Year = year;
	}

	public String getMake() {
		return Make;
	}

	public void setMake(String make) {
		Make = make;
	}

	public String getModel() {
		return Model;
	}

	public void setModel(String model) {
		Model = model;
	}

	public String getColor() {
		return Color;
	}

	public void setColor(String color) {
		Color = color;
	}


	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getAvailable() {
		return Available;
	}

	public void setAvailable(String available) {
		Available = available;
	}

	
	
}
