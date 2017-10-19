package com.rental.servlet;

import java.io.IOException;
import java.sql.Connection;
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
import com.rental.work.DBConnector;
import com.rental.work.ErrorHandling;
import com.rental.work.Work;

/**
 * Servlet implementation class AdminReturnCarServlet
 */
@WebServlet("/AdminReturnCarServlet")
public class AdminReturnCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminReturnCarServlet() {
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
		//create connection with work object and make strings to do the work
		Work work = new Work();
		String confirmation = "You have succsessfully returned the vehicle";
		String url ="returnCar.jsp";
		String errorurl = work.getAdminerror();
		String carid = null;
		String uname = req.getParameter("User_Name");
		String defaulted = "User has no car";

		int rs;
		Connection conn = null;
		java.sql.PreparedStatement st = null;
		Statement sp = null;
		String nativeSQL = "";
		ResultSet result  =null;

		try {
			//create connection with DB
			conn = DBConnector.createConnection();
			sp = conn.createStatement();
			
			conn.setAutoCommit(false);
			result = sp.executeQuery("SELECT * FROM userdetails where User_Name='"+ uname+"'");
			
			if(result.next()) {
				carid= result.getString("Car_Rental");
				if (carid.equals("User has no  car")) {
					confirmation = "User has no vehicle to return";
					work.Confirmation(req, res, confirmation, url);
				}
			}

			st = conn.prepareStatement("update userdetails SET Car_Rental = ? where User_Name= ?");
			st.clearParameters();
			st.setString(1, defaulted);
			st.setString(2, uname);
			rs = st.executeUpdate();
			if (rs !=0) {
				System.out.println("user is set back to not having a car");
			}

			st = conn.prepareStatement("update cardetails SET Availability = 'Available' where id= ?");
			st.clearParameters();
			st.setString(1, carid);
			rs = st.executeUpdate();
			if (rs != 0) {
				System.out.println("car is set back to available");
				
				
			} else {

			} 
			conn.commit();
			work.Confirmation(req, res, confirmation, url);
			return;
			
			
		} catch (Exception e) {
			try{conn.rollback();}catch(Exception e1){}
			//generate new error object and push it to the front.
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