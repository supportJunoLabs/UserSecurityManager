package com.junolabs.usm.persistence.dao;

import java.util.List;

import com.junolabs.usm.model.User;
import com.junolabs.usm.persistence.dao.AbstractDAO;

public interface UserDAO extends AbstractDAO  {
	User getById(long id);
	User getByEmail(String email);
	User getByAccountName(String email);
	List<User> getAll();
	
	User create(User user) throws Exception;
	User update(User user);
	void delete(long id);
}
