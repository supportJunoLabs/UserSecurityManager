package com.junolabs.usm.persistence.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface DAOManager {

	public Connection getConnection() throws SQLException;
	
}
