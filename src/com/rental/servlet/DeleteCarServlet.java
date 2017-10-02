package com.rental.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class DeleteCarServlet
 */
@WebServlet("/DeleteCarServlet")
public class DeleteCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCarServlet() {
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
		
		//get information of the car to be deleted and admins password
		String carid = req.getParameter("id");
		
		
		ResultSet rs =null;
		Connection conn = null;
		Statement st= null;

		try {
			Context    ctx = new InitialContext();
		    Context env = ( Context )ctx.lookup( "java:comp/env" );
		    DataSource ds = ( DataSource )env.lookup( "jdbc/carRentalSystem");
			conn = ds.getConnection();
			st= conn.createStatement();
			
			
			 st.executeUpdate("delete * FROM carrdetails where id='"+ carid+ "'");
					if(((ResultSet) st).next()) {
						RequestDispatcher requestDispatcher = req.getRequestDispatcher("carRentalAdmin.jsp");
						requestDispatcher.forward(req, res);
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

