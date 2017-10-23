package com.rental.work;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rental.models.ConfirmationBean;


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
