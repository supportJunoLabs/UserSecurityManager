package com.junolabs.usm.persistence.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.junolabs.usm.model.User;
import com.junolabs.usm.persistence.dao.AbstractDAO;

public abstract class UserDAO extends AbstractDAO  {
	public abstract User getById(long id, HttpServletRequest request);
	public abstract User getByEmail(String email, HttpServletRequest request);
	public abstract User getByAccountName(String email, HttpServletRequest request);
	public abstract List<User> getAll(HttpServletRequest request);
	
	public abstract User create(User user, HttpServletRequest request) throws Exception;
	public abstract User update(User user, HttpServletRequest request);
	public abstract void delete(long id, HttpServletRequest request);
}
