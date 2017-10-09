package com.rental.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.rental.models.ErrorBean;
import com.rental.models.User;

import java.sql.Statement;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// Get the users information
		User user = new User();
		user.setUser_Name(req.getParameter("User_Name"));
		user.setPassword(req.getParameter("password"));

		// Make connection with the DB to authenticate against it
		ResultSet rs = null;
		Connection conn = null;
		Statement st = null;

		try {
			Context ctx = new InitialContext();
			Context env = (Context) ctx.lookup("java:comp/env");
			DataSource ds = (DataSource) env.lookup("jdbc/carRentalSystem");
			conn = ds.getConnection();
			st = conn.createStatement();

			rs = st.executeQuery("SELECT * FROM userdetails where User_Name='" + user.getUser_Name()
					+ "' and password='" + user.getPassword() + "'");
			if (rs.next()) {
				HttpSession session = req.getSession();
				user.setFirst_name(rs.getString("First_Name"));
				user.setLast_Name(rs.getString("Last_Name"));
				user.setEmail_address(rs.getString("Email_Address"));
				user.setType(rs.getString("User_Type"));
				session.setAttribute("user", user);

				if ("Admin".equalsIgnoreCase(user.getType())) {
					res.sendRedirect("admin/adminHome.jsp");
					conn.close();
				} else {
					res.sendRedirect("user/userhome.jsp");
					conn.close();
					return;
				}
			} else {
				System.out.println("Invalid password, please try again");
				res.sendRedirect("login.jsp");
			}
		} catch (Exception e) {
			ErrorBean errorbean = new ErrorBean();
			errorbean.setError(e);
			res.sendRedirect("userError.jsp");
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
			try {
				if (rs != null)
					rs.close();
			} catch (java.sql.SQLException e) {
			}

		}
	}

}
