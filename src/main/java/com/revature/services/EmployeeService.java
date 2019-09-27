package com.revature.services;

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

import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.repositories.EmployeeDao;
import com.revature.repositories.EmployeeDaoJDBC;
import com.revature.repositories.ReimbursementDao;
import com.revature.repositories.ReimbursementDaoJDBC;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class EmployeeService {
	
	private Employee selectedEmployee = new Employee(0, null, null, null, null, null, null);
	private EmployeeDao employeeDao = new EmployeeDaoJDBC();
	private ReimbursementDao reimbursementDao = new ReimbursementDaoJDBC();
	static Scanner sc = new Scanner(System.in);
	static int id;
	static String typeOfReimbursement;
	static double amount;
	static int employee_id;
	static String emp_username;
	static String  emp_password;
	static String first_name;
	static String last_name;
	static String email;
	static String type;

	static String status = "Pending";
	static String emp_type = "Employee";
	public static ArrayList<Reimbursement> reimbursement = new ArrayList<Reimbursement>();
	public static ArrayList<Employee> employee = new ArrayList<Employee>();
	/*
	 * 
	 */


  public Employee getSelectedEmployee() {
		return selectedEmployee;
	}

	public void setSelectedEmployee(Employee selectedEmployee) {
		this.selectedEmployee = selectedEmployee;
	}
  
	public void submitReimbursement() {

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
		reimbursementDao.createReimbursement(new Reimbursement(id, employee_id, typeOfReimbursement, amount, status));
		reimbursement.add(new Reimbursement(id, employee_id, typeOfReimbursement, amount, status));				
		System.out.println("Thank you for subminting. ");						
		
	}

	public static void viewPendingReimbursement() {
		System.out.println("Pending Reimbursements");
		ReimbursementDao reimbursementDao = new ReimbursementDaoJDBC();
		for(Reimbursement r : reimbursementDao.viewEmployeePending()) {
			if("Pending".equals(r.status) ) {
				//String s = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(r.daterequested);
				System.out.println( r.title + "  " + r.amountrequested + " " + r.status  + "  " + r.daterequested );
			}
			
			
		}
		
	}

	public static void viewResolvedReimbursement() {
		System.out.println("Resolved Reimbursement");
		ReimbursementDao reimbursementDao = new ReimbursementDaoJDBC();
		for(Reimbursement r : reimbursementDao.viewEmployeePending()) {
			if("Approved".equals(r.resolved_status) ) 
			System.out.println( r.title + "  " + r.amountrequested + " " + r.status  + "  " + r.getDaterequested() );
				
		}
		
	}
	/*
	 * 
	 */
	public static void addEmployee() {
		System.out.println("Add new Employee");

		amount = sc.nextInt();
		System.out.println("Enter image for approval: ");
		System.out.println("Thank you for submitting. ");						

	}

	public List<Employee> getEmployees() {
		return employeeDao.viewEmployees();
	}
	
	public void submitReimbursement2() {
		reimbursementDao.createReimbursement(new Reimbursement(0, 0, null, 0, null, null, null));
		System.out.println("Submited Reimbursement Request");
	}

	public void viewPendingReimbursements(int id) {
		System.out.println("Pending Reimbursements");
		reimbursementDao.viewPending(id);
	}

	public void viewResolvedReimbursements(int id) {
		System.out.println("Resolved Reimbursements");
		reimbursementDao.viewResolved(id);
		
	}

	public void addEmployee() {
		System.out.println("Add new Employee");
		System.out.print("Enter First Name: ");
		first_name = sc.next();
		System.out.print("Enter Last Name: ");
		last_name = sc.next();
		System.out.print("Enter Email: ");
		email = sc.next();
		System.out.print("Enter Username: ");
		emp_username = sc.next();
		System.out.print("Enter Password: ");
		emp_password = sc.next();
		System.out.println("Employee has been registered succesfully!");					
		EmployeeDao employeeDao = new EmployeeDaoJDBC();
		employeeDao.createEmployee(new Employee(id, first_name, last_name, email, emp_username, emp_password, emp_type));
		employee.add(new Employee(id, first_name, last_name, email, emp_username, emp_password, emp_type));
	}

	

}
