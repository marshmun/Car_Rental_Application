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
import com.rental.models.Work;

/**
 * Servlet implementation class AdminUpdateCar
 */
@WebServlet("/AdminUpdateCar")
public class AdminUpdateCar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminUpdateCar() {
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
		Work work = new Work();
		String id = req.getParameter("id");
		String year = req.getParameter("Year");
		String make = req.getParameter("Make");
		String model = req.getParameter("Model");
		String color = req.getParameter("Color");

		int rs;
		Connection conn = null;
		java.sql.PreparedStatement st = null;
		String nativeSQL = "";

		try {
			conn = work.createConnection();
			
		

			st = conn.prepareStatement("update cardetails SET Year = ?, Make = ?, Model = ?, Color= ?  where id = ?");
			st.clearParameters();
			st.setString(1, year);
			st.setString(2, make);
			st.setString(3, model);
			st.setString(4, color);
			st.setString(5, id);
			
			
			rs = st.executeUpdate();
			if (rs != 0) {
				res.sendRedirect("carrentaladmin.jsp");
				return;
			} else {

			}
		} catch (Exception e) {
			work.ErrorAdmin(req, res, e);
			ErrorBean errorbean = new ErrorBean();
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
