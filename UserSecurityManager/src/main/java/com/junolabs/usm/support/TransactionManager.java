package com.junolabs.usm.support;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.junolabs.usm.persistence.dao.ConnectionManager;
import com.junolabs.usm.persistence.dao.mysql.ConnectionManagerMySQL;

public class TransactionManager {
	
	private Map<HttpServletRequest, ConnectionManager> mapDAOManager;
	
	// --- Singleton ---
	
	private static TransactionManager INSTANCE = null;
	 
    private TransactionManager() {}
 
    private synchronized static void createInstance(HttpServletRequest request) throws Exception {
        if (INSTANCE == null) { 
            INSTANCE = new TransactionManager();
            INSTANCE.mapDAOManager = new HashMap<HttpServletRequest, ConnectionManager>();
        }
        
        ConnectionManager daoManager;
		try {
			daoManager = new ConnectionManagerMySQL();
			INSTANCE.mapDAOManager.put(request, daoManager);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		
    }
 
    public static TransactionManager getInstance(HttpServletRequest request) throws Exception {
        createInstance(request);
        return INSTANCE;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException(); 
    }
    
	// --- -------- ---
	// --- -------- ---
    
    public void initTransaction(HttpServletRequest request) throws Exception{
    	try {
			this.mapDAOManager.get(request).getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
    }
    
    // --- -------- ---
    
    public void commitTransaction(HttpServletRequest request) throws Exception{
    	try {
			this.mapDAOManager.get(request).getConnection().commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
    }
    
    // --- -------- ---
    
    public void rollbackTransaction(HttpServletRequest request) throws Exception{
    	try {
			this.mapDAOManager.get(request).getConnection().rollback();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
    }
    
    // --- -------- ---
    
    public void finish(HttpServletRequest request) throws Exception{
    	try {
			this.mapDAOManager.get(request).getConnection().close();
			this.mapDAOManager.remove(request);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
    }
    
    // --- -------- ---
}
