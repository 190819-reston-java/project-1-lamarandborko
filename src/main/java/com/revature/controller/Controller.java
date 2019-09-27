package com.revature.controller;

import java.util.Scanner;

import com.revature.model.Employee;
import com.revature.repositories.EmployeeDao;
import com.revature.repositories.EmployeeDaoJDBC;
import com.revature.services.EmployeeService;
import com.revature.services.ManagerService;

public class Controller {
	public static Employee currentEmployee;
	public static Employee employeeType;
	public static String emp_type;
	public static boolean quit = false;
  
	private static Scanner in = new Scanner(System.in);
	private static EmployeeService employeeService = new EmployeeService();
	private static ManagerService managerService = new ManagerService();


	public static void loginMenu() {

		Scanner in = new Scanner(System.in);
		System.out.println("Welcome to the Main Menu");
		System.out.println(" ");
		System.out.println("1. Login Employee");
		System.out.println("2. Login Manager");
		System.out.println("3. Create User Acount");
		System.out.println("4. Exit.");
		String userChoice = in.next();
		switch (userChoice) {

		case "1":
			
			logIn();
			break;
		case "2":
			logIn();
			break;
		case "3":
			createUser();
			break;
		case "4":
			System.out.println("exiting");
			in.close();
			System.exit(0);
			break;
		default:
			Controller.quit = true;
			System.err.println("Option not recognized");
			break;

		}
		loginMenu();
	}
	
	public static void logIn() {
		String emp_username;
		String emp_password;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Your Username: ");;
		emp_username = sc.next();
		System.out.println("Enter Your Password: ");
		emp_password = sc.next();
		EmployeeDao employeeDao = new EmployeeDaoJDBC();
		if (employeeDao.getEmployee(emp_username, emp_password) != null) {
			currentEmployee = employeeDao.getEmployee(emp_username);
	    	System.out.println(currentEmployee.first_name +" welcome to reimbursement App!");
	    	if("Manager".equals(currentEmployee.emp_type)) {
	    		managerMenu();	
	    	}else {
	    		employeeMenu();
	    	}
	            	  							    	  						
	      } else {
	    	 System.err.println("Wrong username or password, try again!");
	    	 System.out.println(" ");
	    	 logIn();// if mismatch calling same method over and over			
		  }	   			
			sc.close();	  
		  
		
	}

	private static void employeeMenu() {
		Scanner in = new Scanner(System.in);
		System.out.println(" ");
		System.out.println("Employee Menu");
		System.out.println(" ");
		System.out.println("1. Submit reimbursement");
		System.out.println("2. View pending reimbursement");
		System.out.println("3. View accepted reimbursement");
		System.out.println("4. View user information");
		System.out.println("5. Exit to main Menu");
		System.out.println("6. Exit.");
		String userChoice = in.next();
		switch (userChoice) {

		case "1":
			employeeService.submitReimbursement();
			break;
		case "2":
			employeeService.viewPendingReimbursements(0);
			break;
		case "3":
			employeeService.viewResolvedReimbursements(0);
			break;
		case "4":
			//Employee.employeeInformation();
			break;
		case "5":
			loginMenu();
			break;
		case "6":
			System.out.println("exiting");
			in.close();
			System.exit(0);
			break;
		default:
			Controller.quit = true;
			System.err.println("Option not recognized");
			break;

		}
		employeeMenu();

	}

	private static void managerMenu() {

		Scanner in = new Scanner(System.in);
		System.out.println(" ");
		System.out.println("Manager Menu");
		System.out.println(" ");
		System.out.println("1: approve/deny pending reimbursement requests");
		System.out.println("2: view pending requests from all employees");
		System.out.println("3: view all resolved requests");
		System.out.println("4: view all employees");
		System.out.println("5: view employee reimbursement requests");
		System.out.println("6: view employee information");
		System.out.println("7: Exit to main menu");
		System.out.println("8: Exit");
		String userChoice = in.next();

		switch (userChoice) {

		case "1":
			managerService.reviewReimbursements();
			break;
		case "2":
			managerService.viewAllPendingReimbursements();
			break;
		case "3":
			managerService.viewAllResolvedReimbursements();
			break;
		case "4":
			managerService.viewAllEmployees();
			break;
		case "5":
			managerService.viewEmployeeRequests();
			break;
		case "6":
			managerService.viewEmployee();
			break;
		case "7":
			loginMenu();
		case "8":
			System.out.println("exiting");
			in.close();
			System.exit(0);
			break;
		default:
			Controller.quit = true;
			System.err.println("Option not recognized");
			break;

		}
		managerMenu();

	}

	private static void createUser() {
		System.out.println("Create New Account");
		employeeService.addEmployee();

	}

}
