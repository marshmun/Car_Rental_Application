package com.rental.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rental.work.ErrorHandling;
import com.rental.dao.MySQLUserDAO;
import com.rental.dao.UserDAO;
import com.rental.models.User;
import com.rental.work.Confirmation;

/**
 * Servlet implementation class AdminUpdateProfile
 */
@WebServlet("/AdminUpdateProfile")
public class AdminUpdateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminUpdateProfile() {
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
		String confirmation = "You have succsessfully added an user to Admin status";
		
		String username = req.getParameter("User_Name");

		if (username.equals("") || username == null) {
			confirmation ="Please provide a valid user name";
			work.getConfirmation(req, res, confirmation, work.ADMINUSER);
		}

		try {
			UserDAO userdao = new MySQLUserDAO();
			
			
			User user = userdao.findByUserName(  username);
			user.setEmailAddress(req.getParameter("Email_Address"));
			user.setFirstName( req.getParameter("First_Name"));
			user.setLastName( req.getParameter("Last_Name"));
			user.setType(req.getParameter("User_Type"));
			
			userdao.updateUser(user.getId(), user);
		}catch(Exception e) {
			ErrorHandling.createtheerror(req, res, e, ErrorHandling.ADMINERROR);
		}
		work.getConfirmation(req, res, confirmation, work.ADMINUSER);
	}
}