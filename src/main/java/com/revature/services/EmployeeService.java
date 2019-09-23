package com.revature.services;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
public class EmployeeService {
	
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

	public static void viewPendingReimbursement() {
		System.out.println("Pending Reimbursement");
		
	}

	public static void viewResolvedReimbursement() {
		System.out.println("Resolved Reimbursement");
		
	}

	public static void addEmployee() {
		System.out.println("Add new Employee");
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
