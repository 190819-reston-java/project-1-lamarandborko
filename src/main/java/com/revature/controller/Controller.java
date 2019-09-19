package com.revature.controller;
import java.util.Scanner;


public class Controller {
	static boolean quit = false;
	public static void loginMenu() {
		Scanner in = new Scanner(System.in);

		do {//looping menu
			System.out.println(" ");
			System.out.println("1. Login Emloyee");
			System.out.println("2. Login Manager");
			System.out.println("3. Create User");
			System.out.println("4. Exit.");

			String userChoice = in.nextLine();
			// When user input option decide where to go
			// if option not recognized go to default
			switch (userChoice) {
			case "1":
				// Calls method usernameAndPassword() in class UsernameAndPassword 
				//then validate user name and password.
				
				break;
			case "2":
				// Call to class AdUser and method addNewUser
				//Prompt to put information in specific order to register.
				
				break;
			case "3":
				
				break;
			case "4":
				//quits a program
				Controller.quit = true;
				System.out.println("Thank you for banking");
				break;
			default:
				System.err.println("Option not recognized");
				break;
			}


		} while (!Controller.quit);//when variable quit is true quits
		in.close();
		
	}

}
