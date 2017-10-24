package com.rental.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rental.work.DBConnector;
import com.rental.work.ErrorHandling;
import com.rental.dao.CarDAO;
import com.rental.dao.MySQLCarDAO;
import com.rental.dao.MySQLUserDAO;
import com.rental.dao.UserDAO;
import com.rental.models.Car;
import com.rental.models.User;
import com.rental.work.Confirmation;

/**
 * Servlet implementation class DeleteUserServlet
 */
@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteUserServlet() {
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
		//creating connection to the work object, and creating strings to do the work.
		Confirmation work = new Confirmation();
		String confirmation = "You have succsessfully added an user to Admin status";


		// get information of the car to be deleted and admins password
		String uname = req.getParameter("User_Name");
		String carid = null;

		Connection conn = null;
		ResultSet result = null;

		try {
			conn = DBConnector.createConnection();
			conn.setAutoCommit(false);
			UserDAO userDao = new MySQLUserDAO();
			
			User user = userDao.findByUserName(uname);
			if(result.next()) {
				carid= result.getString("Car_Rental");
			}
			if(!carid.equals("User has no car")) {
				CarDAO carDao = new MySQLCarDAO();
				Car car = carDao.findById(carid ,conn);
				car.setAvailable("Available");
				carDao.updateCar(car.getId(), car, conn);
			}
			userDao.deleteUser(uname, user, conn);
			
			
			conn.commit();
			work.getConfirmation(req, res, confirmation, work.ADMINUSER);
			
			return;
		} catch (Exception e) {
			//creating new error object and pushing it to the front.
			
			ErrorHandling.createtheerror(req, res, e, ErrorHandling.ADMINERROR);
			
			return;
		} finally {
				try {if (conn != null)conn.close();} catch (java.sql.SQLException e) {
			}

		}
	}
}
