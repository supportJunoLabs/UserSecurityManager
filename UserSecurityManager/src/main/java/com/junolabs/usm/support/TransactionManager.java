package com.junolabs.usm.support;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.junolabs.usm.persistence.dao.DAOManager;
import com.junolabs.usm.persistence.dao.mysql.DAOManagerMySQL;

public class TransactionManager {
	
	private Map<HttpServletRequest, DAOManager> mapDAOManager;
	
	// --- Singleton ---
	
	private static TransactionManager INSTANCE = null;
	 
    private TransactionManager() {}
 
    private synchronized static void createInstance() {
        if (INSTANCE == null) { 
            INSTANCE = new TransactionManager();
            INSTANCE.mapDAOManager = new HashMap<HttpServletRequest, DAOManager>();
        }
    }
 
    public static TransactionManager getInstance() {
        createInstance();
        return INSTANCE;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException(); 
    }
    
	// --- -------- ---
	// --- -------- ---
    
    public void init(HttpServletRequest request) throws Exception {
    	DAOManager daoManager;
		try {
			daoManager = new DAOManagerMySQL();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		
     	this.mapDAOManager.put(request, daoManager);
    }
    
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
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
    }
    
    // --- -------- ---
}
