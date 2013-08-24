package com.junolabs.usm.persistence.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.junolabs.usm.persistence.dao.ConnectionManager;

public class ConnectionManagerMySQL implements ConnectionManager {

	private Connection connection = null;
	
	public ConnectionManagerMySQL() throws SQLException{
		
	}
		
	public Connection getConnection() throws SQLException {
		if (connection == null){
			Properties connectionProps = new Properties();
		    connectionProps.put("user", "root");
		    connectionProps.put("password", "abc123");
		    
		    this.connection = DriverManager.getConnection("jdbc:mysql://localhosr:3306", connectionProps);
		}
		return connection;
	}

}
