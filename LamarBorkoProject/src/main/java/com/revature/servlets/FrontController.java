package com.revature.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.revature.repositories.EmployeeDaoJDBC;
import com.revature.services.EmployeeService;

public class FrontController extends HttpServlet {

	private EmployeeService employeeService;
	
	@Override
	public void init() throws ServletException {
		System.out.println("Starting Front Controller");
		//this.employeeService = new EmployeeService(new EmployeeDaoJDBC());
		super.init();
	}
	
}
