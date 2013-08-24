package com.junolabs.usm.persistence.dao;

import java.util.List;

import com.junolabs.usm.model.Account;

public interface AccountDAO extends AbstractDAO {
	Account getById(long id);
	Account getByEmail(String email);
	Account getByAccountName(String email);
	List<Account> getAll();
	
	Account create(Account account);
	Account update(Account account);
	void delete(long id);
}
