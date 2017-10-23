package com.rental.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rental.work.DBConnector;
import com.rental.work.ErrorHandling;
import com.rental.work.Confirmation;

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
		Confirmation work = new Confirmation();
		String confirmation = "You have succsessfully deleted the vehicle";
		
		// get information of the car to be deleted and admins password
		String carid = req.getParameter("id");
		String nocar = "User has no car";

		int rs;
		Connection conn = null;
		java.sql.PreparedStatement st = null;
		

		try {
			//create connection with the DB
			conn = DBConnector.createConnection();
			

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
				work.getConfirmation(req, res, confirmation, work.ADMINCARRENTAL);
				
				return;
			} else {

			}
		} catch (Exception e) {
			//creating a new error object and pushing it to the front
			
			ErrorHandling.createtheerror(req, res, e, ErrorHandling.ADMINERROR);
			
			
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
