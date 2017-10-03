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
		ResultSet rs =null;
		Connection conn = null;
		Statement st= null;

		try {
		Context    ctx = new InitialContext();
	    Context env = ( Context )ctx.lookup( "java:comp/env" );
	    DataSource ds = ( DataSource )env.lookup( "jdbc/carRentalSystem");
		conn = ds.getConnection();
		st= conn.createStatement();
		
		
		rs= st.executeQuery("SELECT * FROM userdetails where User_Name='"+ userName+ "' and password='" + password + "'" );
				if(rs.next()) {
					HttpSession session = req.getSession();
					session.setAttribute("User_Name", userName);
					type = rs.getString("User_Type");
					session.setAttribute("User_Type", type);
				
					if(type.equals("Admin")) {
						res.sendRedirect("admin/adminHome.jsp");
						conn.close();
					}
					else {
						res.sendRedirect("user/userhome.jsp");
						conn.close();
						return;
					}
		}
				else {
					System.out.println("Invalid password, please try again");
					res.sendRedirect("login.jsp");
				}
	} 
		catch(Exception e) {
			System.out.println(e);
			}
		finally {
            try{ if(st != null ) st.close(); } catch(java.sql.SQLException e){}
            try{ if(conn != null ) conn.close(); } catch(java.sql.SQLException e){}
            try{ if(rs != null) rs.close(); } catch(java.sql.SQLException e){}

		}
	}

}
