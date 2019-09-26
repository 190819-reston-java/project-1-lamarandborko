package com.revature.services;

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
	static String typeOfReimbursement;
	static int amount;
	static ImageIcon image;
	static JLabel label;
	static String username;
	static String  password;
	static String first_name;
	static String last_name;
	static String email;
	static String type;

  public Employee getSelectedEmployee() {
		return selectedEmployee;
	}

	public void setSelectedEmployee(Employee selectedEmployee) {
		this.selectedEmployee = selectedEmployee
  
	public static void submitReimbursement() {
		System.out.println("Submit Reimbursement Here");
		System.out.println(" ");
		System.out.println("What is Reimbursement for? ");
		typeOfReimbursement = sc.next();
		System.out.println("Enter amount to reimburst: ");
		amount = sc.nextInt();
		System.out.println("Enter image for approval: ");
		System.out.println("Thank you for subminting. ");						

	}

	public List<Employee> getEmployees() {
		return employeeDao.viewEmployees();
	}
	
	public void submitReimbursement() {
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
		employeeDao.createEmployee(new Employee(0, null, null, null, null, null, null));
		System.out.print("Enter Username: ");
		username = sc.next();
		System.out.print("Enter Password: ");
		password = sc.next();
		System.out.print("Enter First Name: ");
		first_name = sc.next();
		System.out.print("Enter Last Name: ");
		last_name = sc.next();
		System.out.print("Enter Email: ");
		email = sc.next();
			
	}

	

}
