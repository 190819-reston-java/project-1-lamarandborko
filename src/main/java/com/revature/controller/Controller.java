package com.revature.controller;

import java.util.Scanner;

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
		System.out.println("Employee Menu");
		
	}

	private static void managerMenu() {
		System.out.println("Manager Menu");
		
	}

	private static void createUser() {
		System.out.println("Create New Account");
		
	}
		
	}
