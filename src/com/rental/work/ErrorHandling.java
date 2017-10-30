package com.rental.work;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rental.models.ErrorBean;

public class ErrorHandling {

	
	public static void createtheerror(HttpServletRequest req, HttpServletResponse res, Exception e, String errorurl)
			throws ServletException, IOException {
		ErrorBean errorbean = new ErrorBean();
		errorbean.setError(e);
		req.setAttribute("errorbean", errorbean);
		RequestDispatcher requestDispatcher= req.getRequestDispatcher(errorurl);
		requestDispatcher.forward(req, res);
		System.out.println(e);
			
}
	
	public static final String ADMINERROR = "adminError.jsp";
	public static final String USERERROR ="usererror.jsp";
	public static final String HOMEERROR="homeError.jsp";
}
