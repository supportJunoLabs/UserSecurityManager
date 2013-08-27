package com.junolabs.usm.support;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.junolabs.usm.persistence.dao.ConnectionManager;
import com.junolabs.usm.persistence.dao.TransactionManagerDAO;
import com.junolabs.usm.persistence.dao.mysql.ConnectionManagerMySQL;
import com.junolabs.usm.services.TransactionManagerService;

public class TransactionManager implements TransactionManagerService, TransactionManagerDAO  {
	
	//private Map<HttpServletRequest, ConnectionManager> mapDAOManager;
	private Map<Long, ConnectionManager> mapDAOManager;
	
	// --- Singleton ---
	
	private static TransactionManager INSTANCE = null;
	 
    private TransactionManager() {}
 
    private synchronized static void createInstance(HttpServletRequest request) throws Exception {
        if (INSTANCE == null) { 
            INSTANCE = new TransactionManager();
            // INSTANCE.mapDAOManager = new HashMap<HttpServletRequest, ConnectionManager>();
            INSTANCE.mapDAOManager = new HashMap<Long, ConnectionManager>();
        }
        
        long idCurrentTread = Thread.currentThread().getId();
        
        ConnectionManager daoManager;
		try {
			//if (!INSTANCE.mapDAOManager.containsKey(request)){
			if (!INSTANCE.mapDAOManager.containsKey(idCurrentTread)){
				daoManager = new ConnectionManagerMySQL();
				//INSTANCE.mapDAOManager.put(request, daoManager);
				
				INSTANCE.mapDAOManager.put(idCurrentTread, daoManager);
			}
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
    	long idCurrentTread = Thread.currentThread().getId();
    	try {
			//this.mapDAOManager.get(request).getConnection().setAutoCommit(false);
    		this.mapDAOManager.get(idCurrentTread).getConnection().setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
			//this.mapDAOManager.get(request).getConnection().setAutoCommit(true);
			this.mapDAOManager.get(idCurrentTread).getConnection().setAutoCommit(true);
			throw new Exception(e.getMessage());
		}
    }
    
    // --- -------- ---
    
    public void commitTransaction(HttpServletRequest request) throws Exception{
    	long idCurrentTread = Thread.currentThread().getId();
    	try {
    		//this.mapDAOManager.get(request).getConnection().commit();
    		this.mapDAOManager.get(idCurrentTread).getConnection().commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
    	finally{
    		//this.mapDAOManager.get(request).getConnection().setAutoCommit(true);
    		this.mapDAOManager.get(idCurrentTread).getConnection().setAutoCommit(true);
    	}
    }
    
    // --- -------- ---
    
    public void rollbackTransaction(HttpServletRequest request) throws Exception{
    	long idCurrentTread = Thread.currentThread().getId();
    	try {
			//this.mapDAOManager.get(request).getConnection().rollback();
    		this.mapDAOManager.get(idCurrentTread).getConnection().rollback();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
    	finally{
    		//this.mapDAOManager.get(request).getConnection().setAutoCommit(true);
    		this.mapDAOManager.get(idCurrentTread).getConnection().setAutoCommit(true);
    	}
    }
    
    // --- -------- ---
    
    public void finish(HttpServletRequest request) throws Exception{
    	long idCurrentTread = Thread.currentThread().getId();
    	try {
			//this.mapDAOManager.get(request).getConnection().close();
    		//this.mapDAOManager.remove(request);
    		this.mapDAOManager.get(idCurrentTread).getConnection().close();
			this.mapDAOManager.remove(idCurrentTread);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
    }

    //--------------------------------------------------------------------------------
  //--------------------------------------------------------------------------------
    
	public Connection getConnection(HttpServletRequest request) throws Exception {
		long idCurrentTread = Thread.currentThread().getId();
		return this.mapDAOManager.get(idCurrentTread).getConnection();
	}
    
    // --- -------- ---
}
