package com.lab.usm.controllers;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lab.usm.support.HTTPMethod;
import com.lab.usm.support.MVCRequest;

public abstract class CRUDController {
	
	// Controlador generico para CRUD
	// Implementa el Patron Themplate Method
	
	
	//-------------------------------------------------------------------------------------------------------------------------
	
	private static final String SHOW = "Show";
	private static final String LIST = "List";
	private static final String CREATE = "Create";
	private static final String EDIT = "Edit";
	private static final String DELETE = "Delete";
	
	//-------------------------------------------------------------------------------------------------------------------------
	
	// Estas operaciones cargan parametros genericos que sirven para todos los CRUD de las entidades
	public void show(HTTPMethod httpMethod, MVCRequest mvcRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		callFunction(httpMethod, request, response, SHOW);
		
		forward(mvcRequest, request, response);
    }
	
	public void list(HTTPMethod httpMethod, MVCRequest mvcRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		callFunction(httpMethod, request, response, LIST);
		
		forward(mvcRequest, request, response);
    }
	
	public void create(HTTPMethod httpMethod, MVCRequest mvcRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		callFunction(httpMethod, request, response, CREATE);
		
		forward(mvcRequest, request, response);
    }
	
	public void edit(HTTPMethod httpMethod, MVCRequest mvcRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		callFunction(httpMethod, request, response, EDIT);
		
		forward(mvcRequest, request, response);
    }
	
	public void delete(HTTPMethod httpMethod, MVCRequest mvcRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		callFunction(httpMethod, request, response, DELETE);
		
		forward(mvcRequest, request, response);
    }
	
	//-------------------------------------------------------------------------------------------------------------------------
	
	// Las operaciones de renderizado deben retornar un HTML
	protected abstract void renderShow(HttpServletRequest request, HttpServletResponse response);
	protected abstract void renderList(HttpServletRequest request, HttpServletResponse response) throws IOException;
	protected abstract void renderCreate(HttpServletRequest request, HttpServletResponse response) throws IOException;
	protected abstract void renderEdit(HttpServletRequest request, HttpServletResponse response) throws IOException;
	protected abstract void renderDelete(HttpServletRequest request, HttpServletResponse response) throws IOException;
	
	
	// Las operaciones de procesamiento deben retornar un JSON
	protected abstract void proccessShow(HttpServletRequest request, HttpServletResponse response) throws IOException;
	protected abstract void proccessList(HttpServletRequest request, HttpServletResponse response) throws IOException;
	protected abstract void proccessCreate(HttpServletRequest request, HttpServletResponse response) throws IOException;
	protected abstract void proccessEdit(HttpServletRequest request, HttpServletResponse response) throws IOException;
	protected abstract void proccessDelete(HttpServletRequest request, HttpServletResponse response) throws IOException;
	
	//-------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Según httpMethod, llama a la operación de render (o visualizacion de la página) o a la de procesamiento
	 */
	private void callFunction(HTTPMethod httpMethod, HttpServletRequest request, HttpServletResponse response, String action){
		
		try {
			if (httpMethod == HTTPMethod.GET){
				//CRUDController.class.getMethods();

				//this.getClass().getDeclaredMethods();
				Method method = this.getClass().getDeclaredMethod("render" + action, HttpServletRequest.class, HttpServletResponse.class);
				method.invoke(this, request, response);
			} else if (httpMethod == HTTPMethod.POST){
				Method method = this.getClass().getDeclaredMethod("proccess" + action, HttpServletRequest.class, HttpServletResponse.class);
				method.invoke(this, request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void forward(MVCRequest mvcRequest, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setAttribute("action", mvcRequest.getAction());
		request.setAttribute("controller", mvcRequest.getController().toLowerCase());
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/templates/crudTemplate.jsp");
		requestDispatcher.forward(request, response);
	}
}
