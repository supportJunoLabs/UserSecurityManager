package com.junolabs.usm.persistence.dao.mysql;

import java.util.List;

import com.junolabs.usm.model.User;
import com.junolabs.usm.persistence.dao.UserDAO;

public class UserMySQLDAO implements UserDAO {
	
	// --- Singleton ---
	
	private static UserMySQLDAO INSTANCE = null;
	 
    private UserMySQLDAO() {}
 
    private synchronized static void createInstance() {
        if (INSTANCE == null) { 
            INSTANCE = new UserMySQLDAO();
        }
    }
 
    public static UserMySQLDAO getInstance() {
        createInstance();
        return INSTANCE;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException(); 
    }
    
	// --- -------- ---
	// --- -------- ---

	public User getById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public User getByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	public User getByAccountName(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public User create(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public User update(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}
    
    // --- -------- ---
	    
	    
}
