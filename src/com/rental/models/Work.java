package com.rental.models;

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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class Work {

	public Connection createConnection() throws NamingException, SQLException {
	    // Setup the Database datasource
	    Context ctx = new InitialContext();
	    Context env = (Context) ctx.lookup("java:comp/env");
	    DataSource ds = (DataSource) env.lookup("jdbc/carRentalSystem");
	    return ds.getConnection();
	}

	public void ErrorAdmin(HttpServletRequest req, HttpServletResponse res, Exception e)
			throws ServletException, IOException {
		ErrorBean errorbean = new ErrorBean();
		errorbean.setError(e);
		req.setAttribute("errorbean", errorbean);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("adminError.jsp");
		requestDispatcher.forward(req, res);
		System.out.println(e);
	}

	public void ErrorUser(HttpServletRequest req, HttpServletResponse res, Exception e)
			throws ServletException, IOException {
		ErrorBean errorbean = new ErrorBean();
		errorbean.setError(e);
		req.setAttribute("errorbean", errorbean);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("userError.jsp");
		requestDispatcher.forward(req, res);
		System.out.println(e);
	}
	
	public void ErrorHome(HttpServletRequest req, HttpServletResponse res, Exception e) throws ServletException, IOException {

		ErrorBean errorbean = new ErrorBean();
		errorbean.setError(e);
		req.setAttribute("errorbean", errorbean);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("homeError.jsp");
		requestDispatcher.forward(req, res);
		System.out.println(e);
	}
}
