package com.rental.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.rental.models.Car;
import com.rental.models.CarData;

/**
 * Servlet implementation class GetCarListServlet
 */
@WebServlet("/GetCarListServlet")
public class GetCarListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCarListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 List<Car> cars;
		
		
		ResultSet rs =null;
		Connection conn = null;
		Statement st= null;

		try {
		Context    ctx = new InitialContext();
	    Context env = ( Context )ctx.lookup( "java:comp/env" );
	    DataSource ds = ( DataSource )env.lookup( "jdbc/carRentalSystem");
		conn = ds.getConnection();
		st= conn.createStatement();
		
		
		rs= st.executeQuery("SELECT * FROM carrdetails" );
				if(rs.next()) {
					
				
					Car car = new Car();
					car.setId("id");
					car.setYear("Year");
					car.setMake("Make");
					car.setModel("model");
					car.setColor("Color");
					car.setAvailable("Availability");
					cars.add(car);
					return cars;
				}
		} catch(Exception e) {
			System.out.println(e);
			}
				finally {
					   try{ if(st != null ) st.close(); } catch(java.sql.SQLException e){}
			            try{ if(conn != null ) conn.close(); } catch(java.sql.SQLException e){}
			            try{ if(rs != null) rs.close(); } catch(java.sql.SQLException e){}
				}
				
				
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
