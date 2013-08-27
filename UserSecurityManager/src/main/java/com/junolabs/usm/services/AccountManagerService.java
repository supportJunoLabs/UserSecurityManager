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
	
	public void createAccount(Account account) throws Exception {
		
		try{
			this.transactionManagerService = TransactionManager.getInstance();
			this.transactionManagerService.initTransaction();
			
			User createdUser = userDAO.create(account.getUser());
			account.setUser(createdUser);
			Account createdAccount = accountDAO.create(account);
			
			this.transactionManagerService.commitTransaction();
		}
		catch (Exception exc){
			exc.printStackTrace();
			this.transactionManagerService.rollbackTransaction();
		}
		finally{
			this.transactionManagerService.finish();
		}
	}
	
}
