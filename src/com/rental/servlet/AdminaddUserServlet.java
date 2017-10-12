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

import com.rental.models.ErrorBean;
import com.rental.models.Work;

/**
 * Servlet implementation class AdminaddUserServlet
 */
@WebServlet("/AdminaddUserServlet")
public class AdminaddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminaddUserServlet() {
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
		Work work = new Work();
		// Obtain submitted form data
		String firstName = req.getParameter("First_Name");
		String lastName = req.getParameter("Last_Name");
		String username = req.getParameter("User_Name");
		String email = req.getParameter("Email_Address");
		String password = req.getParameter("Password");

		ResultSet rs = null;
		Connection conn = null;
		Statement st = null;

		try {
			conn = work.createConnection();
			

			// Prepare the SQL statmenet to insert the values
			PreparedStatement stmt = conn.prepareStatement(
					"INSERT INTO userdetails(First_Name, Last_Name, Email_Address, Password, User_Name)  VALUES (?,?,?,?,?)");
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			stmt.setString(3, email);
			stmt.setString(4, password);
			stmt.setString(5, username);

			// Execute the insert
			stmt.executeUpdate();
			conn.close();

			// Dispatch into success page
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("adminUser.jsp");
			requestDispatcher.forward(req, res);
		} catch (Exception e) {
			work.ErrorAdmin(req, res, e);
			ErrorBean errorbean = new ErrorBean();
			
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
