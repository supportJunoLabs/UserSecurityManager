package com.junolabs.usm.services;

import javax.servlet.http.HttpServletRequest;

import com.junolabs.usm.model.Account;
import com.junolabs.usm.model.User;
import com.junolabs.usm.persistence.dao.AccountDAO;
import com.junolabs.usm.persistence.dao.UserDAO;
import com.junolabs.usm.persistence.dao.mysql.AccountMySQLDAO;
import com.junolabs.usm.persistence.dao.mysql.UserMySQLDAO;
import com.junolabs.usm.support.TransactionManager;

public class AccountManagerService extends Service {
	
	private UserDAO userDAO;
	private AccountDAO accountDAO;
	
	// --- Singleton ---

	private static AccountManagerService INSTANCE = null;
	 
    private AccountManagerService() {}
 
    private synchronized static void createInstance(){
        if (INSTANCE == null) { 
            INSTANCE = new AccountManagerService();
            INSTANCE.userDAO = UserMySQLDAO.getInstance();
            INSTANCE.accountDAO = AccountMySQLDAO.getInstance();
        }			
    }
 
    public static AccountManagerService getInstance(){
    	createInstance();
        return INSTANCE;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException(); 
    }
    
	// --- -------- ---
	// --- -------- ---
	
	public void createAccount(Account account, HttpServletRequest request) throws Exception {
		
		try{
			this.transactionManagerService = TransactionManager.getInstance(request);
			this.transactionManagerService.initTransaction(request);
			
			User createdUser = userDAO.create(account.getUser(), request);
			account.setUser(createdUser);
			Account createdAccount = accountDAO.create(account, request);
			
			this.transactionManagerService.commitTransaction(request);
		}
		catch (Exception exc){
			this.transactionManagerService.rollbackTransaction(request);
		}
		finally{
			this.transactionManagerService.finish(request);
		}
	}
	
}
