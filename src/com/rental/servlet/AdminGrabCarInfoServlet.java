package com.rental.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class AdminUpdateCarServlet
 */
@WebServlet("/AdminUpdateCarServlet")
public class AdminGrabCarInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminGrabCarInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String carid = req.getParameter("id");
		String year;
		String make;
		String model;
		String color;
		
		ResultSet rs =null;
		Connection conn = null;
		Statement st= null;

		try {
		Context    ctx = new InitialContext();
	    Context env = ( Context )ctx.lookup( "java:comp/env" );
	    DataSource ds = ( DataSource )env.lookup( "jdbc/carRentalSystem");
		conn = ds.getConnection();
		st= conn.createStatement();
		
		
		rs= st.executeQuery("SELECT * FROM cardetails where id='"+ carid+ "'");
				if(rs.next()) {
					year = rs.getString("Year");
					make = rs.getString("Make");
					model = rs.getString("Model");
					color = rs.getString("color");
					
		}
				else {
					
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
