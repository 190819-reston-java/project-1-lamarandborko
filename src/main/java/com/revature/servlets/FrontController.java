package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Reimbursement;
import com.revature.repositories.ReimbursementDaoJDBC;
import com.revature.services.EmployeeService;
import com.revature.services.ManagerService;

public class FrontController extends HttpServlet {
	
	private EmployeeService employeeService;
	private ManagerService managerService;
	
	@Override
	public void init() throws ServletException {
		System.out.println("Starting Front Controller");
		this.managerService = new ManagerService(new ReimbursementDaoJDBC());
		super.init();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String[] splitURI = req.getRequestURI().split("/");
		String[] tokens = Arrays.copyOfRange(splitURI, 3, splitURI.length);

		System.out.println(Arrays.toString(tokens));

		if (tokens.length == 0) {
			resp.sendError(400, "Usage: /api/reimbursements or /api/rpc");
			return;
		}

		switch (tokens[0]) {
		case "reimbursements":
			handleReimbursements(req, resp, tokens);
			break;
		case "rpc":
			resp.sendError(501);
			break;
		default:
			resp.sendError(404, "Token not recognized: " + tokens[0]);
			break;
		}
	}
	
	private void handleReimbursements(
			HttpServletRequest req, HttpServletResponse resp, String[] tokens
			) throws ServletException, IOException {
		
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = resp.getWriter();
		
		Reimbursement reimbursement = null;
		
		switch(req.getMethod()) {
		case "GET":
			if (tokens.length == 1) {
				String jsonReimbursements = om.writeValueAsString(managerService.viewAllPendingReimbursements());
				pw.write(jsonReimbursements);
			} else {
				String jsonReimbursement = om.writeValueAsString(managerService.viewEmployeeRequests(tokens[1]));
				pw.write(jsonReimbursement);
			}
			break;
		case "POST":
			reimbursement = om.readValue(req.getReader(), Reimbursement.class);
			if(!employeeService.createReimbursement(reimbursement)) {
				resp.sendError(400, "Failed to create Reimbursement");
			} else {
				pw.write("Successful creation");
			}
			break;
		case "PUT":
			reimbursement = om.readValue(req.getReader(), Reimbursement.class);
			if(tokens.length > 1) {
				try {
					reimbursement.setId(Integer.parseInt(tokens[1]));
				}catch (NumberFormatException e) {
					resp.sendError(400, "Must PUT to a valid ID.  PUT by name is not supported.");
				}
			}
			
			if(!managerService.acceptReimbursement(reimbursement)) {
				resp.sendError(400, "Failed to update Reimbursement");
			} else if(!managerService.denyReimbursement(reimbursement)) {
				resp.sendError(400, "Failed to update Reimbursement");
			} else{
				pw.write("Successful update");
			}
			break;
		}
			
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
