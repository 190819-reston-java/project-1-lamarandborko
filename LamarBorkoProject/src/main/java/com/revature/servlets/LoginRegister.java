package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

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
import com.revature.services.EmployeeService;

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
		}else if(submitType.equals("empl_info")) {
			ObjectMapper om = new ObjectMapper();
			PrintWriter pw = resp.getWriter();
			om.writeValueAsString(currentEmployee);
			pw.println("<html><head>"
					+ "<meta charset=\"UTF-8\">\r\n" + 
					"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
					"    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\r\n" + 
					"    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" \r\n" + 
					"    integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">\r\n" + 
					"    <link rel=\"stylesheet\" href=\"main.css\" type=\"text/css\">\r\n" + 
					"    <title>Employee Menu</title>"
					+ "</head><body>"
					+ "<nav class=\"navbar nav-pills navbar-expand-md\">\r\n" + 
					"        <a class=\"nav-link active\" href=\"employee.html\">Back</a>\r\n" + 
					"    </nav>\r\n" + 
					"    <header class=\"header\">\r\n" + 
					"        <h1>Expense Reimbursement System </h1>\r\n" + 
					"        <h1>(ERS)</h1>\r\n" + 
					"    </header><section>");
			pw.println("<h2>User First name: " + currentEmployee.first_name + "</h2>");
			pw.println("<h2>User Last name: " + currentEmployee.last_name + "</h2>");
			pw.println("<h2>User Email: " + currentEmployee.email + "</h2>");
			pw.println("<h2>User username: " + currentEmployee.emp_username + "</h2>");
			pw.println("<h2>User password: " + currentEmployee.emp_password + "</h2>");
			pw.println("</section><footer>Borko and Lamar Project 1 &#174;</footer></body></html>");
			//req.getRequestDispatcher("employee_info.html").forward(req, resp);
		}else if(submitType.equals("pending_reim")) {
			ReimbursementDao reimbursementDao = new ReimbursementDaoJDBC();
			ObjectMapper om = new ObjectMapper();
			PrintWriter pw = resp.getWriter();
			pw.println("<html><body><section>");
			for(Reimbursement r : reimbursementDao.viewEmployeePending()) {
				
			String s = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(r.daterequested);			
		    om.writeValueAsString(r);
		    if("Pending".equals(r.status) )
		    pw.println("<h2>" +r.title + "  " + r.amountrequested + " " + r.status  + "  Date Requested: " + s +"</h2>");	    		    						
		}
			pw.println("</section></body></html>");
		}else if(submitType.equals("resolved_reim")) {
			ReimbursementDao reimbursementDao = new ReimbursementDaoJDBC();
			ObjectMapper om = new ObjectMapper();
			PrintWriter pw = resp.getWriter();
			pw.println("<html><footer>Borko and Lamar Project 1 &#174;</footer><body><section>");
			for(Reimbursement r : reimbursementDao.viewEmployeePending()) {
				
			String s = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(r.daterequested);			
		    om.writeValueAsString(r);
		    if("Resolved".equals(r.status) )
		    pw.println("<h2>" +r.title + "  " + r.amountrequested + " " + r.status + " " +r.resolved_status + "  Date Requested: " + s +"</h2>");	    		    						
		}
			pw.println("</section><footer>Borko and Lamar Project 1 &#174;</footer></body></html>");
		}else {
			req.setAttribute("message", "Data not found, Create account!!");
			req.getRequestDispatcher("new_user.html").forward(req, resp);
		}
	}
	

}
