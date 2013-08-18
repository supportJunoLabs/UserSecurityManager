package com.junolabs.usm.persistence.dao;

import java.util.List;

import com.junolabs.usm.model.User;

public interface UserDAO {
	User getById(long id);
	User getByEmail(String email);
	User getByAccountName(String email);
	List<User> getAll();
	
	User create(User user);
	User update(User user);
	void delete(long id);
}
