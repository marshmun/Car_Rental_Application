package com.rental.servlet;

import java.io.IOException;
import java.sql.Connection;

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
import com.rental.work.DBConnector;
import com.rental.work.ErrorHandling;
import com.rental.work.Work;

/**
 * Servlet implementation class AdminUpdateProfile
 */
@WebServlet("/AdminUpdateProfile")
public class AdminUpdateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminUpdateProfile() {
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
		//creates new connection to work object and strings created to do work.
		Work work = new Work();
		String confirmation = "You have succsessfully added an user to Admin status";
		String url ="adminUser.jsp";
		String errorurl = work.getAdminerror();
		String username = req.getParameter("User_Name");
		String email = req.getParameter("Email_Address");
		String fname = req.getParameter("First_Name");
		String lname = req.getParameter("Last_Name");
		String type = req.getParameter("Type");
		//conditonal to send you back to provide a user name to update a user
		if (username.equals("") || username == null) {
			confirmation ="Please provide a valid user name";
			work.Confirmation(req, res, confirmation, errorurl);
		}

		int rs;
		Connection conn = null;
		java.sql.PreparedStatement st = null;
		String nativeSQL = "";

		try {
			conn = DBConnector.createConnection();
			

			st = conn.prepareStatement("update userdetails SET Email_Address = ?, First_Name = ?, Last_Name = ?, User_Type= ?  where User_Name = ?");
			st.clearParameters();
			st.setString(1, email);
			st.setString(2, fname);
			st.setString(3, lname);
			st.setString(4, type);
			st.setString(5, username);
			
			rs = st.executeUpdate();
			if (rs != 0) {
				work.Confirmation(req, res, confirmation, url);
		
				return;
			} else {

			}
		} catch (Exception e) {
			//new error object created and sent to the front
			ErrorHandling errorHandling = new ErrorHandling();
			errorHandling.createtheerror(req, res, e, errorurl);
			
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

		}
	}
}