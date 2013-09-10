package com.junolabs.usm.persistence.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.junolabs.usm.exceptions.BusinessException;
import com.junolabs.usm.model.Account;
import com.junolabs.usm.persistence.dao.AccountDAO;
import com.junolabs.usm.persistence.dao.IConnectionManager;

public class AccountMySQLDAO extends AccountDAO {

	// --- Singleton ---
	
	private static AccountMySQLDAO INSTANCE = null;
	 
    private AccountMySQLDAO() {
    	super();
    }
 
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

	public List<Account> getAll(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public Account create(Account account) {
		try {
			IConnectionManager conn = transactionManagerDAO.getConnectionManager();
			
			PreparedStatement ps = conn.prepareStatement("insert into accounts (NAME, PASSWORD, USER) values (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, account.getName());
			ps.setString(2, account.getPassword());
			ps.setLong(3, account.getUser().getId());
			
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			int generatedKey = 0;
			while (rs.next()) {
			   generatedKey = rs.getInt(1);
			}
			account.setId(generatedKey);

			return account;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		} finally {
			
		}
	}

	public Account update(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

}
