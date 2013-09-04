package com.junolabs.usm.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.junolabs.usm.model.Account;
import com.junolabs.usm.model.User;
import com.junolabs.usm.services.AccountManagerService;
import com.junolabs.usm.support.HTTPMethod;
import com.junolabs.usm.support.MVCRequest;

public class UserController extends CRUDController {
       
    public UserController() {
        super();
        System.out.println("constructor UserController");
    }

	@Override
	protected void renderShow(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("renderShow");
		System.out.println("==========");
		
		AccountManagerService accountManagerService = AccountManagerService.getInstance();
		
		User user = new User();
		user.setFirstName("Leandro");
		user.setLastName("Carrasco");
		user.setBirthDate(new Date());
		user.setEmail("a@b.com");
		
		Account account = new Account();
		account.setName("lcarrasco");
		account.setPassword("abc123");
		account.setUser(user);
		
		try {
			accountManagerService.createAccount(account);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void renderList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
	}

	@Override
	protected void renderCreate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
	}

	@Override
	protected void renderEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
	}

	@Override
	protected void renderDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
	}

	@Override
	protected void proccessShow(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter printWriter = response.getWriter();
		printWriter.println("SHOW PROCCESS");
	}

	@Override
	protected void proccessList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter printWriter = response.getWriter();
		printWriter.println("LIST PROCCESS");
	}

	@Override
	protected void proccessCreate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter printWriter = response.getWriter();
		printWriter.println("CREATE PROCCESS");
		
	}

	@Override
	protected void proccessEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter printWriter = response.getWriter();
		printWriter.println("EDIT PROCCESS");
		
	}

	@Override
	protected void proccessDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter printWriter = response.getWriter();
		printWriter.println("DELETE PROCCESS");

	}
    
    public void particularMethod(HTTPMethod httpMethod, MVCRequest mvcRequest, HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter printWriter = response.getWriter();
		printWriter.println("PARTICULAR METHOD");
    }

}
