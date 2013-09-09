package com.junolabs.usm.persistence.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.junolabs.usm.exceptions.BusinessException;
import com.junolabs.usm.model.User;
import com.junolabs.usm.persistence.dao.IConnectionManager;
import com.junolabs.usm.persistence.dao.TransactionManagerDAO;
import com.junolabs.usm.persistence.dao.UserDAO;
import com.junolabs.usm.support.TransactionManager;

public class UserMySQLDAO extends UserDAO {
	
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
		System.out.println("getById");
		
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

	public List<User> getAll(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public User create(User user) {
		try {
			TransactionManagerDAO transactionManagerDAO = TransactionManager.getInstance();
			IConnectionManager conn = transactionManagerDAO.getConnectionManager();
			
			
			PreparedStatement ps = conn.prepareStatement("insert into users (FIRST_NAME, LAST_NAME, EMAIL, BIRTH_DATE) values (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getEmail());
			ps.setDate(4, new java.sql.Date(user.getBirthDate().getTime()));
			
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			int generatedKey = 0;
			while (rs.next()) {
			   generatedKey = rs.getInt(1);
			}
			user.setId(generatedKey);

			return user;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
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
