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
		HttpSession session = req.getSession(true);
		User user = (User) session.getAttribute("user");

		String email = req.getParameter("Email_Address");
		String fname = req.getParameter("First_Name");
		String lname = req.getParameter("Last_Name");
		if (email.equals("") || email.equals(null)) {
			email = user.getEmail_address();
		}
		if (fname.equals("") || fname.equals(null)) {
			fname = user.getFirst_name();
		}
		if (lname.equals("") || lname.equals(null)) {
			lname = user.getLast_Name();
		}

		int rs;
		Connection conn = null;
		java.sql.PreparedStatement st = null;
		String nativeSQL = "";

		try {
			Context ctx = new InitialContext();
			Context env = (Context) ctx.lookup("java:comp/env");
			DataSource ds = (DataSource) env.lookup("jdbc/carRentalSystem");
			conn = ds.getConnection();

			st = conn.prepareStatement("update userdetails SET Email_Address = '" + email + "', First_Name ='" + fname
					+ "', Last_Name ='" + lname + "'  where User_Name ='" + user.getUser_Name() + "'");
			st.clearParameters();
			rs = st.executeUpdate();
			if (rs != 0) {
				user.setFirst_name(fname);
				user.setLast_Name(lname);
				user.setEmail_address(email);
				res.sendRedirect("update.jsp");
				return;
			} else {

			}
		} catch (Exception e) {
			ErrorBean errorbean = new ErrorBean();
			errorbean.setError(e);
			req.setAttribute("errorbean", errorbean);
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("userError.jsp");
			requestDispatcher.forward(req, res);
			System.out.println(e);
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