package com.junolabs.usm.persistence.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.junolabs.usm.model.Account;

public abstract class AccountDAO extends AbstractDAO {
	public abstract Account getById(long id, HttpServletRequest request);
	public abstract Account getByEmail(String email, HttpServletRequest request);
	public abstract Account getByAccountName(String email, HttpServletRequest request);
	public abstract List<Account> getAll(HttpServletRequest request);
	
	public abstract Account create(Account account, HttpServletRequest request) throws Exception;
	public abstract Account update(Account account, HttpServletRequest request);
	public abstract void delete(long id, HttpServletRequest request);
}
