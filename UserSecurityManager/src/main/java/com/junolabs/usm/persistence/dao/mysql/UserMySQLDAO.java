package com.junolabs.usm.persistence.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.junolabs.usm.exceptions.BusinessException;
import com.junolabs.usm.model.User;
import com.junolabs.usm.persistence.dao.IConnectionManager;
import com.junolabs.usm.persistence.dao.UserDAO;
import com.junolabs.usm.persistence.dao.mysql.support.MySQLUtils;
import com.junolabs.usm.persistence.dao.mysql.support.PersistenceError;

public class UserMySQLDAO extends UserDAO {
	
	private static final String ID = "ID";
	private static final String FIRST_NAME = "FIRST_NAME";
	private static final String LAST_NAME = "LAST_NAME";
	private static final String EMAIL = "EMAIL";
	private static final String BIRTH_DATE = "BIRTH_DATE";
	private static final String USERS = "users";
	
	// --- Singleton ---
	
	private static UserMySQLDAO INSTANCE = null;
	 
    private UserMySQLDAO() {
    	super();
    }
 
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
    
    //-----------------------------------------------------------------------------------------------------------

	public User getById(long id) {
		try {
			IConnectionManager conn = transactionManagerDAO.getConnectionManager();
			
			String strSelect = "select * from " + USERS + "where " + ID + "=" + id;
			PreparedStatement ps = conn.prepareStatement(strSelect);
			ResultSet rs = ps.executeQuery();
			
			User user = null;
			
			while (rs.next()) {
				user = new User();
				populateUser(user, rs);
			}
			
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}
	
	//-----------------------------------------------------------------------------------------------------------

	public User getByEmail(String email) {
		try {
			IConnectionManager conn = transactionManagerDAO.getConnectionManager();
			
			String strSelect = "select * from " + USERS + "where " + EMAIL + "=" + email;
			PreparedStatement ps = conn.prepareStatement(strSelect);
			ResultSet rs = ps.executeQuery();
			
			User user = null;
			
			while (rs.next()) {
				user = new User();
				populateUser(user, rs);
			}
			
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}
	
	//-----------------------------------------------------------------------------------------------------------

	public User getByAccountName(String accountName) {
		// TODO Auto-generated method stub
		return null;
	}

	//-----------------------------------------------------------------------------------------------------------
	
	public List<User> getAll() {
		try {
			IConnectionManager conn = transactionManagerDAO.getConnectionManager();
			
			String strSelect = "select * from " + USERS;
			PreparedStatement ps = conn.prepareStatement(strSelect);
			ResultSet rs = ps.executeQuery();
			
			List<User> users = new LinkedList<User>();
			
			while (rs.next()) {
				User user = new User();
				populateUser(user, rs);
				users.add(user);
			}
			
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}
	
	//-----------------------------------------------------------------------------------------------------------

	public User create(User user) {
		try {
			IConnectionManager conn = transactionManagerDAO.getConnectionManager();
			
			String strInsert = MySQLUtils.prepareInsert(USERS, FIRST_NAME, LAST_NAME, EMAIL, BIRTH_DATE);
			PreparedStatement ps = conn.prepareStatement(strInsert, PreparedStatement.RETURN_GENERATED_KEYS);
			
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
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	//-----------------------------------------------------------------------------------------------------------
	
	public User update(User user) {
		try {
			IConnectionManager conn = transactionManagerDAO.getConnectionManager();
			
			HashMap<String, Object> mapColumnValue = new HashMap<String, Object>();
			mapColumnValue.put(FIRST_NAME, user.getFirstName());
			mapColumnValue.put(LAST_NAME, user.getLastName());
			mapColumnValue.put(EMAIL, user.getEmail());
			mapColumnValue.put(BIRTH_DATE, MySQLUtils.getStringDate(user.getBirthDate()));
			
			String strUpdate = MySQLUtils.prepareUpdate(USERS,  user.getId(), FIRST_NAME, LAST_NAME, EMAIL, BIRTH_DATE);
			PreparedStatement ps = conn.prepareStatement(strUpdate, PreparedStatement.RETURN_GENERATED_KEYS);
			
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
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	//-----------------------------------------------------------------------------------------------------------
	
	public void delete(long id) {
		try {
			IConnectionManager conn = transactionManagerDAO.getConnectionManager();
			
			String strInsert = MySQLUtils.prepareDelete(USERS);
			PreparedStatement ps = conn.prepareStatement(strInsert, PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setLong(1, id);
			
			int result = ps.executeUpdate();
			
			if (result == 0){
				throw new BusinessException("No se pudo eliminar Usuario con id " + id, PersistenceError.ERROR_DELETE);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}
    
	//-----------------------------------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------------------------------
	
	private void populateUser(User user, ResultSet rs) throws SQLException{
		user = new User();
		user.setId(rs.getLong(ID));
		user.setFirstName(rs.getString(FIRST_NAME));
		user.setLastName(rs.getString(LAST_NAME));
		user.setEmail(rs.getString(EMAIL));
		user.setBirthDate(rs.getDate(BIRTH_DATE));
	}
	
	//-----------------------------------------------------------------------------------------------------------
	    
	    
}
