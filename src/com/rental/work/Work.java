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

public class Work {

	private ServletRequest req;

//	public Connection createConnection() throws NamingException, SQLException {
//	    // Setup the Database datasource
//	    Context ctx = new InitialContext();
//	    Context env = (Context) ctx.lookup("java:comp/env");
//	    DataSource ds = (DataSource) env.lookup("jdbc/carRentalSystem");
//	    return ds.getConnection();
//	}
	
	public void ErrorHandling(HttpServletRequest req, HttpServletResponse res, Exception e, String errorurl)
			throws ServletException, IOException {
		ErrorBean errorbean = new ErrorBean();
		errorbean.setError(e);
		req.setAttribute("errorbean", errorbean);
		RequestDispatcher requestDispatcher= req.getRequestDispatcher(errorurl);
		requestDispatcher.forward(req, res);
		System.out.println(e);
				
	}
	
	public String adminerror = "adminError.jsp";
	public String usererror ="usererror.jsp";
	public String homeerror="homeError.jsp";
	
	

	public String getAdminerror() {
		return adminerror;
	}

	public void setAdminerror(String adminerror) {
		this.adminerror = adminerror;
	}

	public String getUsererror() {
		return usererror;
	}

	public void setUsererror(String usererror) {
		this.usererror = usererror;
	}

	public String getHomeerror() {
		return homeerror;
	}

	public void setHomeerror(String homeerror) {
		this.homeerror = homeerror;
	}

	
	
	public void Confirmation(HttpServletRequest req, HttpServletResponse res, String confirmation, String url) throws ServletException, IOException {
		
		ConfirmationBean confirmationbean = new ConfirmationBean();
		confirmationbean.setConfirmation(confirmation);
		req.setAttribute("confirmation", confirmationbean);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher(url);
		requestDispatcher.forward(req, res);
	}
	
}
