package com.rental.work;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.rental.models.ConfirmationBean;
import com.rental.models.ErrorBean;

public class Confirmation {
	
	
	
	public void getConfirmation(HttpServletRequest req, HttpServletResponse res, String confirmation, String url) throws ServletException, IOException {
		
		ConfirmationBean confirmationbean = new ConfirmationBean();
		confirmationbean.setConfirmation(confirmation);
		req.setAttribute("confirmation", confirmationbean);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher(url);
		requestDispatcher.forward(req, res);
	}
	
	public final String ADMINUSER = "adminUser.jsp";
	public final String ADMINCARRENTAL ="carrentaladmin.jsp";
	public final String RETURNCAR = "returnCar.jsp";
	public final String USERCARRENTAL ="carRental.jsp";
	public final String UPDATEUSERINFO = "update.jsp";
	
}
