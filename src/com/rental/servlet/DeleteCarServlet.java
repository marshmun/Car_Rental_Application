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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.rental.models.ErrorBean;
import com.rental.models.Work;

/**
 * Servlet implementation class DeleteCarServlet
 */
@WebServlet("/DeleteCarServlet")
public class DeleteCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteCarServlet() {
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
		//create connection with work object and creating strings to do the work.
		Work work = new Work();
		String confirmation = "You have succsessfully deleted the vehicle";
		String url ="carrentaladmin.jsp";
		String errorurl = work.getAdminerror();
		
		// get information of the car to be deleted and admins password
		String carid = req.getParameter("id");
		String nocar = "User has no car";

		int rs;
		Connection conn = null;
		java.sql.PreparedStatement st = null;
		String nativeSQL = "";

		try {
			//create connection with the DB
			conn = work.createConnection();
			

			st = conn.prepareStatement("update userdetails set Car_rental = ? where Car_Rental = ?");
			st.clearParameters();
			st.setString(1, nocar);
			st.setString(2, carid);
			
			rs = st.executeUpdate();

			st = conn.prepareStatement("delete FROM cardetails where id= ?");
			st.clearParameters();
			st.setString(1, carid);
			
			rs = st.executeUpdate();
			if (rs != 0) {
				work.Confirmation(req, res, confirmation, url);
				
				return;
			} else {

			}
		} catch (Exception e) {
			//creating a new error object and pushing it to the front
			work.ErrorHandling(req, res, e, errorurl);
			
			
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
