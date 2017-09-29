package com.rental.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.sql.Statement;
import com.rental.models.User;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		HttpSession session = req.getSession();
		session.setAttribute("user", user);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	//Get the users information
		String userName = req.getParameter("User_Name");
		String password = req.getParameter("password");
		String type ="";
		//Make connection with the DB to authenticate against it
		try {
		Context    ctx = new InitialContext();
	    Context env = ( Context )ctx.lookup( "java:comp/env" );
	    DataSource ds = ( DataSource )env.lookup( "jdbc/carRentalSystem");
		Connection conn = ds.getConnection();
		Statement st= conn.createStatement();
		ResultSet rs;
		
		rs= st.executeQuery("SELECT * FROM userdetails where User_Name='"+ userName+ "' and password='" + password + "'" );
				if(rs.next()) {
				HttpSession session = req.getSession();
				session.setAttribute("User_Name", userName);
				type = rs.getString("User_Type");
				session.setAttribute("User_Type", type);
				if(type.equals("Admin")) {
				res.sendRedirect("admin/adminHome.html");
				}
				else {
					res.sendRedirect("user/userhome.html");
					return;
				}
		}
				else {
					System.out.println("Invalid password, please try again");
					res.sendRedirect("login.html");
				}
	} catch(Exception e) 
		{System.out.println(e);}
		finally {}
	}

}
