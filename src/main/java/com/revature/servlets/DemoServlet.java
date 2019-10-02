package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.repositories.ReimbursementDaoJDBC;
import com.revature.services.ManagerService;

public class DemoServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ManagerService managerService =
				new ManagerService(new ReimbursementDaoJDBC());
		
		resp.getWriter().write(managerService.viewAllPendingReimbursements().get(0).toString());
	}

}
