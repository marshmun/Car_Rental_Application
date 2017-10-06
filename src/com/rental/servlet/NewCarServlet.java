package com.rental.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class NewCarServlet
 */
@WebServlet("/NewCarServlet")
public class NewCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewCarServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// get the required car information from the form
		String year = req.getParameter("Year");
		String make = req.getParameter("Make");
		String model = req.getParameter("Model");
		String color = req.getParameter("Color");

		ResultSet rs = null;
		Connection conn = null;
		Statement st = null;

		try {
			// Setup the Database datasource
			Context ctx = new InitialContext();
			Context env = (Context) ctx.lookup("java:comp/env");
			DataSource ds = (DataSource) env.lookup("jdbc/carRentalSystem");
			conn = ds.getConnection();

			// Prepare the SQL statmenet to insert the values
			PreparedStatement stmt = conn
					.prepareStatement("INSERT INTO cardetails(Year, Make, Model, Color)  VALUES (?,?,?,?)");
			stmt.setString(1, year);
			stmt.setString(2, make);
			stmt.setString(3, model);
			stmt.setString(4, color);

			// Execute the insert
			stmt.executeUpdate();
			conn.close();

			// Dispatch into success page
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("carrentaladmin.jsp");
			requestDispatcher.forward(req, res);

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (java.sql.SQLException e) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (java.sql.SQLException e) {
			}
			try {
				if (rs != null)
					rs.close();
			} catch (java.sql.SQLException e) {
			}

		}
	}

}
