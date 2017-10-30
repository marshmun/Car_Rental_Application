package com.rental.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rental.dao.MySQLUserDAO;
import com.rental.dao.UserDAO;
import com.rental.models.User;
import com.rental.work.ErrorHandling;

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

		UserDAO userdao = new MySQLUserDAO();
		User user1 = new User();
		user1.setUserName(req.getParameter("User_Name"));
		user1.setPassword(req.getParameter("password"));

		try {
			User user = userdao.login(req, res, user1);
			
			
		} catch (Exception e) {
			ErrorHandling.createtheerror(req, res, e, ErrorHandling.HOMEERROR);
		} 
		
	}

}
