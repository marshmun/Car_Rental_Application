package com.rental.models;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnector {
	public Connection createConnection() throws NamingException, SQLException {
	    // Setup the Database datasource
	    Context ctx = new InitialContext();
	    Context env = (Context) ctx.lookup("java:comp/env");
	    DataSource ds = (DataSource) env.lookup("jdbc/carRentalSystem");
	    return ds.getConnection();
	}

}
