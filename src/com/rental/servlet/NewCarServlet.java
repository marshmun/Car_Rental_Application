package com.rental.servlet;

import java.io.IOException;



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

/**
 * Servlet implementation class NewCarServlet
 */
@WebServlet("/NewCarServlet")
public class NewCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewCarServlet() {
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
		//creating work object and strings to do the work.
		Confirmation work = new Confirmation();
		String confirmation = "You have succsessfully created a new vehicle";
		Car car = new Car();
		car.setYear(req.getParameter("Year"));
		car.setMake(req.getParameter("Make"));
		car.setModel(req.getParameter("Model"));
		car.setColor(req.getParameter("Color"));
		

		CarDAO cardao = new MySQLCarDAO();
		try {
			cardao.insertCar(car);
		}catch(Exception e) {
			ErrorHandling.createtheerror(req, res, e, ErrorHandling.ADMINERROR);
		}
		work.getConfirmation(req, res, confirmation, work.ADMINCARRENTAL);
	}

}
