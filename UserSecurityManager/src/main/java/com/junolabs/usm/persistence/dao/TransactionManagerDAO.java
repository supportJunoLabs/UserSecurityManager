package com.junolabs.usm.persistence.dao;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

public interface TransactionManagerDAO {
	public Connection getConnection() throws Exception;
}
