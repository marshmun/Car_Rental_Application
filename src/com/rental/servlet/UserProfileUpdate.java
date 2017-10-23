package com.rental.servlet;

import java.io.IOException;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rental.dao.MySQLUserDAO;
import com.rental.dao.UserDAO;
import com.rental.models.User;
import com.rental.work.ErrorHandling;
import com.rental.work.Confirmation;

/**
 * Servlet implementation class UserProfileUpdate
 */
@WebServlet("/UserProfileUpdate")
public class UserProfileUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserProfileUpdate() {
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
		//get the session and pull out user information
		HttpSession session = req.getSession(true);
		UserDAO userdao = new MySQLUserDAO();
		
		User user = userdao.findByUserName( session.getAttribute("user"));
		
		Confirmation work = new Confirmation();
		String confirmation = "You have succsessfully updated your profile";

		String email = req.getParameter("Email_Address");
		String fname = req.getParameter("First_Name");
		String lname = req.getParameter("Last_Name");
		
		//conditonals if nothing is put in the for the following params
		if (email.equals("") || email == null) {
			email = user.getEmailAddress();
		}
		if (fname.equals("") || fname == null) {
			fname = user.getFirstName();
		}
		if (lname.equals("") || lname == null) {
			lname = user.getLastName();
		}
		user.setEmailAddress(email);
		user.setFirstName(fname);
		user.setLastName(lname);
		try {
			userdao.updateUser(user.getId(), user);
		}catch(Exception e) {
			ErrorHandling.createtheerror(req, res, e, ErrorHandling.ADMINERROR);
		}
		work.getConfirmation(req, res, confirmation, work.ADMINUSER);
		
	}
}