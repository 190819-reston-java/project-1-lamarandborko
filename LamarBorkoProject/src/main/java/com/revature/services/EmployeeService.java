package com.revature.services;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import com.revature.controller.Controller;
import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.repositories.EmployeeDao;
import com.revature.repositories.EmployeeDaoJDBC;
import com.revature.repositories.ReimbursementDao;
import com.revature.repositories.ReimbursementDaoJDBC;
public class EmployeeService {
	
	static Scanner sc = new Scanner(System.in);
	static int id;
	public static String typeOfReimbursement;
	public static double amount;
	public static int employee_id;
	static String username;
	static String  password;
	public static String first_name;
	public static String last_name;
	static String email;
	static String type;
	public static Timestamp daterequested;
	public static String status = "Pending";
	public static String resolved_status;
	public static int resolved_by;
	static String emp_type = "Employee";
	public static ArrayList<Reimbursement> reimbursement = new ArrayList<Reimbursement>();
	public static ArrayList<Employee> employee = new ArrayList<Employee>();
	/*
	 * 
	 */
	public static void submitReimbursement() {
		System.out.println("Submit Reimbursement Here");
		System.out.println(" ");
		System.out.println("What is Reimbursement for? ");
		typeOfReimbursement = sc.next();
		System.out.println("Enter amount to reimburst: ");
		amount = sc.nextDouble();
		//System.out.println("Enter image for approval: ");
		
		ReimbursementDao reimbursementDao = new ReimbursementDaoJDBC();
		employee_id = Controller.currentEmployee.id;
		typeOfReimbursement = "Reimbursement is for: " + typeOfReimbursement;
		System.out.println(employee_id);
		System.out.println(typeOfReimbursement);				
		reimbursementDao.createReimbursement(new Reimbursement(id, employee_id, typeOfReimbursement, amount,daterequested, status, resolved_status, resolved_by));
		reimbursement.add(new Reimbursement(id, employee_id, typeOfReimbursement, amount,daterequested, status, resolved_status, resolved_by));				
		System.out.println("Thank you for submitting. ");						
		
	}

	public static void viewPendingReimbursement() {
		System.out.println("Pending Reimbursements");
		ReimbursementDao reimbursementDao = new ReimbursementDaoJDBC();
		for(Reimbursement r : reimbursementDao.viewEmployeePending()) {
			String s = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(r.daterequested);
			if("Pending".equals(r.status) ) 
			System.out.println( r.title + "  " + r.amountrequested + " " + r.status  + " Date Requested: " + s );
			
		}
		
	}

	public static void viewResolvedReimbursement() {
		System.out.println("Resolved Reimbursement");
		ReimbursementDao reimbursementDao = new ReimbursementDaoJDBC();
		for(Reimbursement r : reimbursementDao.viewEmployeePending()) {
			String s = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(r.daterequested);
			if("Approved".equals(r.resolved_status) ) 
			System.out.println( r.title + "  " + r.amountrequested + " " + r.status  + "  Date Requested: " + s );
				
		}
		
	}
	/*
	 * 
	 */
	public static void addEmployee() {
		System.out.println("Add new Employee");
		System.out.print("Enter First Name: ");
		first_name = sc.next();
		System.out.print("Enter Last Name: ");
		last_name = sc.next();
		System.out.print("Enter Email: ");
		email = sc.next();
		System.out.print("Enter Username: ");
		username = sc.next();
		System.out.print("Enter Password: ");
		password = sc.next();
		System.out.println("Employee has been registered succesfully!");					
		EmployeeDao employeeDao = new EmployeeDaoJDBC();
		employeeDao.createEmployee(new Employee(id, first_name, last_name, email, username, password, emp_type));
		employee.add(new Employee(id, first_name, last_name, email, username, password, emp_type));
	}

	public static void employeeInformation() {
		EmployeeDao employeeDao =new EmployeeDaoJDBC();
		for(Employee e : employeeDao.viewEmployees()) {
			System.out.println("Employee: " + e.first_name +" "+
		e.last_name + " Email: " +e.email + " Password: " + e.emp_password);
		}
		
		
	}

	

}
