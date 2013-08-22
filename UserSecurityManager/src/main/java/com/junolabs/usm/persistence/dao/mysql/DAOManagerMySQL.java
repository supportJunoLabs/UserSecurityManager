package com.junolabs.usm.persistence.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.junolabs.usm.persistence.dao.DAOManager;

public class DAOManagerMySQL implements DAOManager {

	private Connection connection;
	
	public DAOManagerMySQL() throws SQLException{
		Properties connectionProps = new Properties();
	    connectionProps.put("user", "root");
	    connectionProps.put("password", "abc123");
	    
	    this.connection = DriverManager.getConnection("jdbc:mysql://localhosr:3306", connectionProps);
	}

		
	public Connection getConnection() throws SQLException {
		return connection;
	}

}
