package com.junolabs.usm.persistence.dao.mysql;

import com.junolabs.usm.persistence.dao.AccountDAO;
import com.junolabs.usm.persistence.dao.ConnectionManager;
import com.junolabs.usm.persistence.dao.FactoryDAO;
import com.junolabs.usm.persistence.dao.UserDAO;

public class FactoryMySQLDAO extends FactoryDAO {

	// --- Singleton ---
	private static FactoryMySQLDAO INSTANCE = null;
	 
    private FactoryMySQLDAO() {}
 
    private synchronized static void createInstance() {
        if (INSTANCE == null) { 
            INSTANCE = new FactoryMySQLDAO();
        }
    }
 
    public static FactoryMySQLDAO getInstance() {
        createInstance();
        return INSTANCE;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException(); 
    }
    
    // --- -------- ---
	
	public ConnectionManager getConnectionManager() {
		return ConnectionManagerMySQL.getInstance();
	}
    
	public UserDAO getUserDAO() {
		return UserMySQLDAO.getInstance();
	}

	public AccountDAO getAccountDAO() {
		return AccountMySQLDAO.getInstance();
	}
	
	
}
