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
import com.rental.models.User;

import javafx.scene.control.Alert;

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
		HttpSession session = req.getSession(true);
		User user = (User) session.getAttribute("user");

		String carid = req.getParameter("id");

		int rs;
		Connection conn = null;
		java.sql.PreparedStatement st = null;
		String nativeSQL = "";

		try {
			Context ctx = new InitialContext();
			Context env = (Context) ctx.lookup("java:comp/env");
			DataSource ds = (DataSource) env.lookup("jdbc/carRentalSystem");
			conn = ds.getConnection();

			st = conn.prepareCall("update userdetails SET Car_Rental = '" + carid + "' where User_Name='"
					+ user.getUser_Name() + "'");
			st.clearParameters();
			rs = st.executeUpdate();

			st = conn.prepareStatement("update cardetails SET Availability = 'Unavailable' where id='" + carid + "'");
			st.clearParameters();
			rs = st.executeUpdate();
			if (rs != 0) {
				res.sendRedirect("carRental.jsp");
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