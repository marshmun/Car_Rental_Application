package com.rental.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

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
 * Servlet implementation class AdminRentOutServlet
 */
@WebServlet("/AdminRentOutServlet")
public class AdminRentOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminRentOutServlet() {
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
		String confirmation = "You have succsessfully rented out the vehicle";
		String url ="carrentaladmin.jsp";
		String errorurl = work.getAdminerror();
		String carid = req.getParameter("id");
		String username = req.getParameter("User_Name");

		int rs;
		Connection conn = null;
		java.sql.PreparedStatement st = null;
		String nativeSQL = "";

		try {
			//connect with the DB
			conn = DBConnector.createConnection();
			

			st = conn.prepareCall("update userdetails SET Car_Rental = ? where User_Name= ?");
			st.clearParameters();
			st.setString(1, carid);
			st.setString(2, username);
			rs = st.executeUpdate();

			st = conn.prepareStatement("update cardetails SET Availability = 'Unavailable' where id=?");
			st.clearParameters();
			st.setString(1, carid);
			rs = st.executeUpdate();
			if (rs != 0) {
				work.Confirmation(req, res, confirmation, url);
				
				return;
			} else {

			}
		} catch (Exception e) {
			//create error object and push it to the front
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