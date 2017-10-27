package com.rental.servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rental.dao.MySQLUserDAO;
import com.rental.dao.UserDAO;
import com.rental.models.User;
import com.rental.work.ErrorHandling;


/**
 * Servlet implementation class RegistrationServlet
 */

public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrationServlet() {
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
		
		
		User user = new User();
		user.setFirstName(req.getParameter("First_Name"));
		user.setLastName(req.getParameter("Last_Name"));
		user.setUserName(req.getParameter("User_Name"));
		user.setEmailAddress(req.getParameter("Email_Address"));
		user.setPassword(req.getParameter("Password"));
		
		UserDAO userdao = new MySQLUserDAO();
		try {
			userdao.insertUser(user);
			res.sendRedirect("login.jsp");
		}catch(Exception e) {
			ErrorHandling.createtheerror(req, res, e,ErrorHandling.HOMEERROR);
		}
		
	
		
	}

}
