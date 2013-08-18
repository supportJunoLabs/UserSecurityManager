package com.junolabs.usm.persistence.factory;

import com.junolabs.usm.persistence.dao.UserDAO;
import com.junolabs.usm.persistence.dao.mysql.UserMySQLDAO;

public class FactoryDAO {
	
	// --- Singleton ---
	private static FactoryDAO INSTANCE = null;
	 
    private FactoryDAO() {}
 
    private synchronized static void createInstance() {
        if (INSTANCE == null) { 
            INSTANCE = new FactoryDAO();
        }
    }
 
    public static FactoryDAO getInstance() {
        createInstance();
        return INSTANCE;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException(); 
    }
    
    // --- -------- ---
    
    public UserDAO getUserDAO(){
    	return UserMySQLDAO.getInstance();
    }
}
