package com.junolabs.usm.persistence.dao.postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.junolabs.usm.persistence.dao.ConnectionManager;
import com.junolabs.usm.persistence.dao.FactoryDAO;

public class ConnectionManagerPostgres implements ConnectionManager {
	
	// --- Singleton ---
	private static ConnectionManagerPostgres INSTANCE = null;
	 
    private ConnectionManagerPostgres() {}
 
    private synchronized static void createInstance() {
        if (INSTANCE == null) { 
            INSTANCE = new ConnectionManagerPostgres();
        }
    }
 
    public static ConnectionManagerPostgres getInstance() {
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
	    connectionProps.put("user", "postgres");
	    connectionProps.put("password", "abc123");
	    
	    try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
	    return DriverManager.getConnection("jdbc:postgresql://localhost:5432/usm", connectionProps);

	}

}
