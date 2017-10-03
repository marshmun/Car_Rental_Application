package com.rental.models;

public class User {
	
	private String First_name;
	private String Last_Name;
	private String User_Name;
	private String Email_address;
	private String Type;
	private String Password;
	
	public User() {
		super();
	}

	public String getFirst_name() {
		return First_name;
	}

	public void setFirst_name(String first_name) {
		First_name = first_name;
	}

	public String getLast_Name() {
		return Last_Name;
	}

	public void setLast_Name(String last_Name) {
		Last_Name = last_Name;
	}

	public String getUser_Name() {
		return User_Name;
	}

	public void setUser_Name(String user_Name) {
		User_Name = user_Name;
	}

	public String getEmail_address() {
		return Email_address;
	}

	public void setEmail_address(String email_address) {
		Email_address = email_address;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
	
	

}
