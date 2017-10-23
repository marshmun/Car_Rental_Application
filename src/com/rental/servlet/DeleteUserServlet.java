package com.rental.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rental.work.DBConnector;
import com.rental.work.ErrorHandling;
import com.rental.work.Confirmation;

/**
 * Servlet implementation class DeleteUserServlet
 */
@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteUserServlet() {
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
		//creating connection to the work object, and creating strings to do the work.
		Confirmation work = new Confirmation();
		String confirmation = "You have succsessfully added an user to Admin status";


		// get information of the car to be deleted and admins password
		String uname = req.getParameter("User_Name");
		Integer carid = null;

		int rs;
		Connection conn = null;
		java.sql.PreparedStatement st = null;
		Statement sp = null;
		
		ResultSet result  =null;

		try {
			//creating connection with the DB
			conn = DBConnector.createConnection();
			sp = conn.createStatement();
			
			conn.setAutoCommit(false);
			result = sp.executeQuery("SELECT * FROM userdetails where User_Name='"+ uname+"'");
			if(result.next()) {
				carid= result.getInt("Car_Rental");
			}
			if(!carid.equals("User has no car")) {
				st = conn.prepareStatement("update cardetails SET Availability = 'Available' where id= ?");
				st.clearParameters();
				st.setInt(1, carid);
				
				rs = st.executeUpdate();
				if (rs != 0) {
					System.out.println("car is set back to available");
			}
			}
			st = conn.prepareStatement("delete FROM userdetails where User_Name= ?");
			st.clearParameters();
			st.setString(1, uname);
			
			rs = st.executeUpdate();
			if (rs != 0) {
				
				
				
			} else {

			}
			conn.commit();
			work.getConfirmation(req, res, confirmation, work.ADMINUSER);
			
			return;
		} catch (Exception e) {
			//creating new error object and pushing it to the front.
			
			ErrorHandling.createtheerror(req, res, e, ErrorHandling.ADMINERROR);
			
			return;
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
