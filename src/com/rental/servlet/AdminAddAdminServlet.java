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
import com.rental.work.Confirmation;

/**
 * Servlet implementation class AdminAddAdminServlet
 */
@WebServlet("/AdminAddAdminServlet")
public class AdminAddAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminAddAdminServlet() {
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
		//create connection with work object and create strings to do the work
		Confirmation work = new Confirmation();
		
		String confirmation = "You have succsessfully added an user to Admin status";
		
		String username = req.getParameter("User_Name");
		
		int rs;
		Connection conn = null;
		java.sql.PreparedStatement st = null;
		String nativeSQL = "";

		try {
			//create a connection with the db
			conn = DBConnector.createConnection();

			st = conn.prepareStatement("update userdetails SET User_Type = 'Admin' where User_Name= ?");
			st.clearParameters();
			st.setString(1, username);
			rs = st.executeUpdate();
			if (rs != 0) {
				work.getConfirmation(req, res, confirmation, work.ADMINUSER);
				return;
			} else {

			}
		} catch (Exception e) {
			//create new error object and push it to the front
			ErrorHandling errorHandling = new ErrorHandling();
			errorHandling.createtheerror(req, res, e, errorHandling.getADMINERROR());
			
			
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