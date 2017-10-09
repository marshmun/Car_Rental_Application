package com.rental.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

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
		Integer carid = null;
		String uname = req.getParameter("User_Name");
		String defaulted = "User has no car";

		int rs;
		Connection conn = null;
		java.sql.PreparedStatement st = null;
		Statement sp = null;
		String nativeSQL = "";
		ResultSet result  =null;

		try {
			Context ctx = new InitialContext();
			Context env = (Context) ctx.lookup("java:comp/env");
			DataSource ds = (DataSource) env.lookup("jdbc/carRentalSystem");
			conn = ds.getConnection();
			sp = conn.createStatement();
			
			conn.setAutoCommit(false);
			result = sp.executeQuery("SELECT * FROM userdetails where User_Name='"+ uname+"'");
			if(result.next()) {
				carid= result.getInt("Car_Rental");
				if (carid.equals("User has no car")) {
					System.out.println("User has no vehicle to return");
					res.sendRedirect("returnCar.jsp");
				}
			}

			st = conn.prepareStatement("update userdetails SET Car_Rental ='" + defaulted+ "' where User_Name='" + uname+ "' ");
			st.clearParameters();
			rs = st.executeUpdate();

			st = conn.prepareStatement("update cardetails SET Availability = 'Available' where id='" + carid+ "'");
			st.clearParameters();
			rs = st.executeUpdate();
			if (rs != 0) {
				res.sendRedirect("returnCar.jsp");
				return;
			} else {

			} 
			conn.commit();
			
			
		} catch (Exception e) {
			try{conn.rollback();}catch(Exception e1){}
			e.printStackTrace();
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