package com.junolabs.usm.services;

public interface TransactionManagerService {
	public void initTransaction() throws Exception;
    public void commitTransaction() throws Exception;
    public void rollbackTransaction() throws Exception;
    public void finish() throws Exception;
}
