package com.junolabs.usm.persistence.dao.mysql;

import java.util.List;

import com.junolabs.usm.model.Account;
import com.junolabs.usm.persistence.dao.AccountDAO;

public class AccountMySQLDAO implements AccountDAO {

	// --- Singleton ---
	
	private static AccountMySQLDAO INSTANCE = null;
	 
    private AccountMySQLDAO() {}
 
    private synchronized static void createInstance() {
        if (INSTANCE == null) { 
            INSTANCE = new AccountMySQLDAO();
        }
    }
 
    public static AccountMySQLDAO getInstance() {
        createInstance();
        return INSTANCE;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException(); 
    }
    
	// --- -------- ---
	// --- -------- ---
	
	public Account getById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Account getByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	public Account getByAccountName(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Account> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public Account create(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	public Account update(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

}
