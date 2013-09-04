package com.junolabs.usm.support;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.junolabs.usm.persistence.dao.ConnectionManager;
import com.junolabs.usm.persistence.dao.FactoryDAO;
import com.junolabs.usm.persistence.dao.TransactionManagerDAO;
import com.junolabs.usm.services.TransactionManagerService;

public class TransactionManager implements TransactionManagerService, TransactionManagerDAO  {
	
	
	private Map<Long, Connection> mapDAOManager;
	
	// --- Singleton ---
	
	private static TransactionManager INSTANCE = null;
	 
    private TransactionManager() {}
 
    private synchronized static void createInstance() {
        if (INSTANCE == null) { 
            INSTANCE = new TransactionManager();
            INSTANCE.mapDAOManager = new HashMap<Long, Connection>();
        }
        
        long idCurrentTread = Thread.currentThread().getId();
        
		if (!INSTANCE.mapDAOManager.containsKey(idCurrentTread)){
			try {			
				ConnectionManager connectionManager = FactoryDAO.getFactoryDAO().getConnectionManager();
				Connection connection = connectionManager.getConnection();
				INSTANCE.mapDAOManager.put(idCurrentTread, connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
    		Connection connection = this.mapDAOManager.get(idCurrentTread);
    		connection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
			this.mapDAOManager.get(idCurrentTread).setAutoCommit(true);
			throw new Exception(e.getMessage());
		}
    }
    
    // --- -------- ---
    
    public void commitTransaction() throws Exception{
    	long idCurrentTread = Thread.currentThread().getId();
    	try {
    		this.mapDAOManager.get(idCurrentTread).commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
    	finally{
    		this.mapDAOManager.get(idCurrentTread).setAutoCommit(true);
    	}
    }
    
    // --- -------- ---
    
    public void rollbackTransaction() throws Exception{
    	long idCurrentTread = Thread.currentThread().getId();
    	try {
    		this.mapDAOManager.get(idCurrentTread).rollback();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
    	finally{
    		this.mapDAOManager.get(idCurrentTread).setAutoCommit(true);
    	}
    }
    
    // --- -------- ---
    
    public void finish() throws Exception{
    	long idCurrentTread = Thread.currentThread().getId();
    	try {
    		this.mapDAOManager.get(idCurrentTread).close();
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
		return this.mapDAOManager.get(idCurrentTread);
	}
    
    // --- -------- ---
}
