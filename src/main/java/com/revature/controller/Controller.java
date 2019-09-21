package com.revature.controller;
import java.util.Scanner;
import com.revature.model.Employee;
import com.revature.services.EmployeeService;
import com.revature.services.ManagerService;

public class Controller {
	
	public static boolean quit = false;
	
	public static void loginMenu() {
	
		Scanner in = new Scanner(System.in);
		System.out.println(" ");
        System.out.println("1. Login Employee");
        System.out.println("2. Login Manager");
        System.out.println("3. Create User Acount");
        System.out.println("4. Exit.");
        String userChoice = in.next();
        switch (userChoice) {
        
        case "1":
        	employeeMenu();
            break;
        case "2":
        	managerMenu();
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

	private static void employeeMenu() {
		Scanner in = new Scanner(System.in);
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
        	EmployeeService.submitReimbursement();
            break;			
        case "2":
        	EmployeeService.viewPendingReimbursement();
            break;
        case "3":
        	EmployeeService.viewResolvedReimbursement();
            break;
        case "4":
        	Employee.employeeInformation();
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
        	ManagerService.reviewReimbursements();
            break;			
        case "2":
        	ManagerService.viewAllPendingReimbursements();
            break;
        case "3":
        	ManagerService.viewResolvedReimbursements();
            break;
        case "4":
        	ManagerService.viewAllEmployees();
            break;
        case "5":
        	ManagerService.viewEmployeeRequests();
            break;
        case "6":
        	ManagerService.viewEmployee();
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
		EmployeeService.addEmployee();
		
	}
		
	}
