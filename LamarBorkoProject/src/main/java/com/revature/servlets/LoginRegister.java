package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.repositories.EmployeeDao;
import com.revature.repositories.EmployeeDaoJDBC;
import com.revature.repositories.ReimbursementDao;
import com.revature.repositories.ReimbursementDaoJDBC;

@WebServlet("/LoginRegister")
public class LoginRegister extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static Employee currentEmployee;
	public EmployeeDao employee;
	static int id;
	public static String resolved_status;
	public static Timestamp daterequested;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EmployeeDao employeeDao = new EmployeeDaoJDBC();
		String emp_username = req.getParameter("emp_username");
		String emp_password = req.getParameter("emp_password");
		String first_name = req.getParameter("first_name");
		String last_name = req.getParameter("last_name");
		String email = req.getParameter("email");
		String username = req.getParameter("emp_username");
		String password = req.getParameter("emp_password");
		String emp_type = "Employee";
		String amount = req.getParameter("amountrequested");		
		String title = req.getParameter("title");
		String submitType = req.getParameter("submit");
		
		
		Employee employee = employeeDao.getEmployee(emp_username, emp_password);
		if(submitType.equals("login") && (employee != null)) {
			req.setAttribute("message", employee.getFirst_name());			
			currentEmployee = employeeDao.getEmployee(emp_username);
			System.out.println(currentEmployee.first_name);
	    	if("Manager".equals(currentEmployee.emp_type)) {
	    		req.getRequestDispatcher("manager.html").forward(req, resp);
	    		PrintWriter out = resp.getWriter();
	    		out.println("<html><body><section><h2>");
	    		out.println("hi " + employeeDao.getEmployee(currentEmployee.first_name));
	    		out.println("</h2></section></body></html>");
	    	}else {
	    		req.getRequestDispatcher("employee.html").forward(req, resp);
	    	}					
		}else if(submitType.equals("register")) {				
				employeeDao.createEmployee(new Employee(id, first_name, last_name, email, username, password, emp_type));
				req.setAttribute("message", "thank you for registering log in");
				req.getRequestDispatcher("registration_sucess.html").forward(req, resp);
						
		}else if(submitType.equals("submit_reim")) {
			ReimbursementDao reimbursementDao = new ReimbursementDaoJDBC();
			String typeOfReimbursement = "Reimbursement is for: " + title;
			double amountrequested = Integer.parseInt(amount);
			String	status  = "Pending"	;			
			reimbursementDao.createReimbursement(new Reimbursement(id, currentEmployee.id, typeOfReimbursement, amountrequested,
					daterequested, status, resolved_status));
			req.getRequestDispatcher("employee.html").forward(req, resp);				
//		}else if(submitType.equals("view_reim")) {
//			ObjectMapper om = new ObjectMapper();
//			PrintWriter pw = resp.getWriter();
			

		}else {
			req.setAttribute("message", "Data not found, Create account!!");
			req.getRequestDispatcher("new_user.html").forward(req, resp);
		}
	}
	

}
