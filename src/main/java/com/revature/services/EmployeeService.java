package com.revature.services;

import java.util.ArrayList;
import java.util.Scanner;
import com.revature.controller.Controller;
import com.revature.model.Reimbursement;
import com.revature.repositories.ReimbursementDao;
import com.revature.repositories.ReimbursementDaoJDBC;
public class EmployeeService {
	
	static Scanner sc = new Scanner(System.in);
	static int id;
	static String typeOfReimbursement;
	static double amount;
	static int employee_id;
	static String username;
	static String  password;
	static String first_name;
	static String last_name;
	static String email;
	static String type;
	public static ArrayList<Reimbursement> reimbursement = new ArrayList<Reimbursement>();

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
			
			
			reimbursementDao.createReimbursement(new Reimbursement(id, employee_id, typeOfReimbursement, amount));
			reimbursement.add(new Reimbursement(id, employee_id, typeOfReimbursement, amount));
			
		
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
