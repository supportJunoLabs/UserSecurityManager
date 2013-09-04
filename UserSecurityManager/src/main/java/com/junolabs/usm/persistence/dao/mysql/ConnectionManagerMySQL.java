package com.junolabs.usm.persistence.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.junolabs.usm.persistence.dao.ConnectionManager;

public class ConnectionManagerMySQL implements ConnectionManager {
	
	// --- Singleton ---
	private static ConnectionManagerMySQL INSTANCE = null;
	 
    private ConnectionManagerMySQL() {}
 
    private synchronized static void createInstance() {
        if (INSTANCE == null) { 
            INSTANCE = new ConnectionManagerMySQL();
        }
    }
 
    public static ConnectionManagerMySQL getInstance() {
        createInstance();
        return INSTANCE;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException(); 
    }
    
    // --- -------- ---
		
	public Connection getConnection() throws SQLException {

		Properties connectionProps = new Properties();
	    connectionProps.put("user", "root");
	    connectionProps.put("password", "abc123");
	    
	    try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
	    return DriverManager.getConnection("jdbc:mysql://localhost:3306/usm", connectionProps);

	}

}
