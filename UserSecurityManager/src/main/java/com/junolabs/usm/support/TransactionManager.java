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
	
	private Map<Long, ConnectionManager> mapDAOManager;
	
	// --- Singleton ---
	
	private static TransactionManager INSTANCE = null;
	 
    private TransactionManager() {}
 
    private synchronized static void createInstance() throws Exception {
        if (INSTANCE == null) { 
            INSTANCE = new TransactionManager();
            INSTANCE.mapDAOManager = new HashMap<Long, ConnectionManager>();
        }
        
        long idCurrentTread = Thread.currentThread().getId();
        
        ConnectionManager daoManager;
		try {
			if (!INSTANCE.mapDAOManager.containsKey(idCurrentTread)){
				daoManager = new ConnectionManagerMySQL();
				INSTANCE.mapDAOManager.put(idCurrentTread, daoManager);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		
    }
 
    public static TransactionManager getInstance() throws Exception {
        createInstance();
        return INSTANCE;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException(); 
    }
    
	// --- -------- ---
	// --- -------- ---
    
    public void initTransaction() throws Exception{
    	long idCurrentTread = Thread.currentThread().getId();
    	try {
    		this.mapDAOManager.get(idCurrentTread).getConnection().setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
			this.mapDAOManager.get(idCurrentTread).getConnection().setAutoCommit(true);
			throw new Exception(e.getMessage());
		}
    }
    
    // --- -------- ---
    
    public void commitTransaction() throws Exception{
    	long idCurrentTread = Thread.currentThread().getId();
    	try {
    		this.mapDAOManager.get(idCurrentTread).getConnection().commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
    	finally{
    		this.mapDAOManager.get(idCurrentTread).getConnection().setAutoCommit(true);
    	}
    }
    
    // --- -------- ---
    
    public void rollbackTransaction() throws Exception{
    	long idCurrentTread = Thread.currentThread().getId();
    	try {
    		this.mapDAOManager.get(idCurrentTread).getConnection().rollback();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
    	finally{
    		this.mapDAOManager.get(idCurrentTread).getConnection().setAutoCommit(true);
    	}
    }
    
    // --- -------- ---
    
    public void finish() throws Exception{
    	long idCurrentTread = Thread.currentThread().getId();
    	try {
    		this.mapDAOManager.get(idCurrentTread).getConnection().close();
			this.mapDAOManager.remove(idCurrentTread);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
    }

    //--------------------------------------------------------------------------------
  //--------------------------------------------------------------------------------
    
	public Connection getConnection() throws Exception {
		long idCurrentTread = Thread.currentThread().getId();
		return this.mapDAOManager.get(idCurrentTread).getConnection();
	}
    
    // --- -------- ---
}
