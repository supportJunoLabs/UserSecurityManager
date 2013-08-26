package com.junolabs.usm.services;

import javax.servlet.http.HttpServletRequest;

public interface TransactionManagerService {
	public void initTransaction(HttpServletRequest request) throws Exception;
    public void commitTransaction(HttpServletRequest request) throws Exception;
    public void rollbackTransaction(HttpServletRequest request) throws Exception;
    public void finish(HttpServletRequest request) throws Exception;
}
