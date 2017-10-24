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
 * Servlet implementation class AdminRentOutServlet
 */
@WebServlet("/AdminRentOutServlet")
public class AdminRentOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminRentOutServlet() {
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
		//create connection with work object and make strings to do the work
		Confirmation work = new Confirmation();
		String confirmation = "You have succsessfully rented out the vehicle";
		
		String carid = req.getParameter("id");
		String username = req.getParameter("User_Name");

		Connection conn = null;
		try {
			//connect with the DB
			conn = DBConnector.createConnection();
			conn.setAutoCommit(false);
			UserDAO userDao = new MySQLUserDAO();
			
			User user = userDao.findByUserName(username, conn); 
			user.setCarRental(carid);
			userDao.updateUser(user.getId(), user, conn);
			
			CarDAO carDao = new MySQLCarDAO();
			Car car = carDao.findById(carid, conn);
			car.setAvailable("Unavailable");
			carDao.updateCar(car.getId(), car, conn);

			
			conn.commit();
			
			work.getConfirmation(req, res, confirmation, work.ADMINCARRENTAL);
										
		} catch (Exception e) {
			try {if(conn != null) conn.rollback();} catch(Exception e1) {}
			ErrorHandling.createtheerror(req, res, e, ErrorHandling.ADMINERROR);
			
		} finally {
			try { if (conn != null)	conn.close();} catch (java.sql.SQLException e) {}
		}
	}
}