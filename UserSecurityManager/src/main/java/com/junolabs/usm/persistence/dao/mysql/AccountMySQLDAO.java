package com.junolabs.usm.persistence.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.junolabs.usm.model.Account;
import com.junolabs.usm.persistence.dao.AccountDAO;
import com.junolabs.usm.persistence.dao.TransactionManagerDAO;
import com.junolabs.usm.support.TransactionManager;

public class AccountMySQLDAO extends AccountDAO {

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
	
	public Account getById(long id, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public Account getByEmail(String email, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public Account getByAccountName(String email, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Account> getAll(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public Account create(Account account, HttpServletRequest request) throws Exception {
		try {
			TransactionManagerDAO transactionManagerDAO = TransactionManager.getInstance(request);
			Connection conn = transactionManagerDAO.getConnection(request);
			
			
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
			throw new Exception();
		}
	}

	public Account update(Account account, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(long id, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

}
