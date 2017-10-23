package com.rental.servlet;

import java.io.IOException;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.rental.dao.MySQLUserDAO;
import com.rental.dao.UserDAO;
import com.rental.work.ErrorHandling;
import com.rental.work.Confirmation;

/**
 * Servlet implementation class AdminaddUserServlet
 */
@WebServlet("/AdminaddUserServlet")
public class AdminaddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminaddUserServlet() {
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
		//create new work object and strings to do the work
		Confirmation work = new Confirmation();
		String confirmation = "You have succsessfully added a user";
		
		String firstName = req.getParameter("First_Name");
		String lastName = req.getParameter("Last_Name");
		String username = req.getParameter("User_Name");
		String email = req.getParameter("Email_Address");
		String password = req.getParameter("Password");
		UserDAO userdao = new MySQLUserDAO();
		try {
			userdao.insertUser(firstName, lastName, email, username, password);
		}catch (Exception e){
			ErrorHandling.createtheerror(req, res, e,ErrorHandling.ADMINERROR);
		}
		work.getConfirmation(req, res, confirmation, work.ADMINUSER);
		
		// Obtain submitted form data
		

		
	}

}
