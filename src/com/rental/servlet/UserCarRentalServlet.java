package com.rental.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rental.models.User;
import com.rental.work.DBConnector;
import com.rental.work.ErrorHandling;
import com.rental.work.Confirmation;



/**
 * Servlet implementation class UserCarRentalServlet
 */
@WebServlet("/UserCarRentalServlet")
public class UserCarRentalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserCarRentalServlet() {
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
	@SuppressWarnings("resource")
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Confirmation work = new Confirmation();
		String confirmation = "You have succsessfully rented out the vehicle";

		
		HttpSession session = req.getSession(true);
		User user = (User) session.getAttribute("user");
		String carid = req.getParameter("id");


		int rs;
		Connection conn = null;
		java.sql.PreparedStatement st = null;
		

		try {
			conn = DBConnector.createConnection();

			st = conn.prepareCall("update userdetails SET Car_Rental = '" + carid + "' where User_Name='" + user.getUserName() + "'");
			st.clearParameters();
			rs = st.executeUpdate();

			st = conn.prepareStatement("update cardetails SET Availability = 'Unavailable' where id= ?");
			st.clearParameters();
			st.setString(1, carid);
			
			rs = st.executeUpdate();
			if (rs != 0) {
				work.getConfirmation(req, res, confirmation, work.USERCARRENTAL);
				return;
			} else {

			}
		} catch (Exception e) {
		
			ErrorHandling.createtheerror(req, res, e, ErrorHandling.USERERROR);
			
			
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