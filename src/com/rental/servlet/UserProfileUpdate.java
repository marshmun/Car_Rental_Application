package com.rental.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.mail.Session;
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
import com.rental.models.User;
import com.rental.work.DBConnector;
import com.rental.work.ErrorHandling;
import com.rental.work.Confirmation;

/**
 * Servlet implementation class UserProfileUpdate
 */
@WebServlet("/UserProfileUpdate")
public class UserProfileUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserProfileUpdate() {
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
		//get the session and pull out user information
		HttpSession session = req.getSession(true);
		User user = (User) session.getAttribute("user");
		
		//connect with the work object and create strings to do the work
		
		Confirmation work = new Confirmation();
		String confirmation = "You have succsessfully updated your profile";

		String email = req.getParameter("Email_Address");
		String fname = req.getParameter("First_Name");
		String lname = req.getParameter("Last_Name");
		
		//conditonals if nothing is put in the for the following params
		if (email.equals("") || email == null) {
			email = user.getEmailAddress();
		}
		if (fname.equals("") || fname == null) {
			fname = user.getFirstName();
		}
		if (lname.equals("") || lname == null) {
			lname = user.getLastName();
		}

		int rs;
		Connection conn = null;
		java.sql.PreparedStatement st = null;
		String nativeSQL = "";

		try {
			//connect to the DB
			conn = DBConnector.createConnection();
			

			st = conn.prepareStatement("update userdetails SET Email_Address = ?, First_Name = ?, Last_Name = ?  where User_Name = ?");
			st.clearParameters();
			st.setString(1, email);
			st.setString(2, fname);
			st.setString(3, lname);
			st.setString(4, user.getUserName());
			
			rs = st.executeUpdate();
			if (rs != 0) {
				user.setFirstName(fname);
				user.setLastName(lname);
				user.setEmailAddress(email);
				work.getConfirmation(req, res, confirmation, work.UPDATEUSERINFO);
				return;
			} else {

			}
		} catch (Exception e) {
			//create new error object and push it to the front
			ErrorHandling errorHandling = new ErrorHandling();
			errorHandling.createtheerror(req, res, e, errorHandling.USERERROR);
			
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