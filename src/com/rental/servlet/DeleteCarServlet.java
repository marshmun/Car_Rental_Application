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
import com.rental.dao.CarDAO;
import com.rental.dao.MySQLCarDAO;
import com.rental.dao.MySQLUserDAO;
import com.rental.dao.UserDAO;
import com.rental.models.Car;
import com.rental.models.User;
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
		
		Confirmation work = new Confirmation();
		String confirmation = "You have succsessfully deleted the vehicle";
		
	
		
		
		String carid = req.getParameter("id");
		String nocar = "User has no car";

		Connection conn = null;

		try {
			conn = DBConnector.createConnection();
			conn.setAutoCommit(false);
			UserDAO userDao = new MySQLUserDAO();
			
			
				User user = userDao.findByCarRental(carid,conn);
			if(user != null) {
				user.setCarRental(nocar);
				userDao.updateUser(user.getId(), user, conn);
			}
			
			
			CarDAO carDao = new MySQLCarDAO();
			Car car =carDao.findById(carid, conn);
			car.setId(carid);
			carDao.deleteCar(car.getId(), car, conn);
		
			
			conn.commit();
			work.getConfirmation(req, res, confirmation, work.ADMINCARRENTAL);
		} catch (Exception e) {
			//creating a new error object and pushing it to the front
			try {if(conn !=null) conn.rollback();}catch(Exception e1) {}
			ErrorHandling.createtheerror(req, res, e, ErrorHandling.ADMINERROR);
			
			
		} finally {
			try {if (conn != null)conn.close();} catch (java.sql.SQLException e) {
			}

		}
	}
}
