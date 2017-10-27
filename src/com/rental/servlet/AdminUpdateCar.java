package com.rental.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.rental.work.ErrorHandling;
import com.rental.dao.CarDAO;
import com.rental.dao.MySQLCarDAO;
import com.rental.models.Car;
import com.rental.work.Confirmation;
import com.rental.work.DBConnector;

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

		Confirmation work = new Confirmation();
		String confirmation = "You have succsessfully updated vehicle";
		
		String id = req.getParameter("id");
		String year = req.getParameter("Year");
		String make = req.getParameter("Make");
		String model = req.getParameter("Model");
		String color = req.getParameter("Color");
		
		Connection conn = null;
		try {
			conn = DBConnector.createConnection();
			
			CarDAO cardao = new MySQLCarDAO();
			Car car = cardao.findById(id, conn);
			car.setYear(year);
			car.setMake(make);
			car.setModel(model);
			car.setColor(color);
			cardao.updateCar(car.getId(), car, conn);
			
		}catch(Exception e) {
			ErrorHandling.createtheerror(req, res, e, ErrorHandling.ADMINERROR);
			
		}
		work.getConfirmation(req, res, confirmation, work.ADMINCARRENTAL);
	}
}
