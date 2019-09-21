package com.revature.controller;
import java.util.Scanner;
import com.revature.model.Employee;
import com.revature.services.EmployeeService;

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
		System.out.println("Manager Menu");
		
	}

	private static void createUser() {
		System.out.println("Create New Account");
		EmployeeService.addEmployee();
		
	}
		
	}
