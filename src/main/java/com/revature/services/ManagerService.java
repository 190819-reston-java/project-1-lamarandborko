package com.revature.services;

import java.util.List;
import java.util.Scanner;

import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.repositories.EmployeeDao;
import com.revature.repositories.EmployeeDaoJDBC;
import com.revature.repositories.ReimbursementDao;
import com.revature.repositories.ReimbursementDaoJDBC;

public class ManagerService {

	private Employee selectedEmployee = new Employee(0, null, null, null, null, null, null);
	private EmployeeDao employeeDao = new EmployeeDaoJDBC();
	private ReimbursementDao reimbursementDao = new ReimbursementDaoJDBC();
	private static Scanner sc = new Scanner(System.in);
	private Reimbursement selectedReimbursement = new Reimbursement(0, 0, null, 0, null, null, null, null);

	// list of all pending
	public List<Reimbursement> getAllPending() {
		reimbursementDao.viewAllPending().forEach((Reimbursement r) -> {
			System.out.println(r);
		});
		return reimbursementDao.viewAllPending();
	}

	// list of all resolved
	public List<Reimbursement> getAllResolved() {
		reimbursementDao.viewAllPending().forEach((Reimbursement r) -> {
			System.out.println(r);
		});
		return reimbursementDao.viewAllPending();
	}

	// list of pending by name
	public List<Reimbursement> getPending(String name) {
		reimbursementDao.viewPending(name).forEach((Reimbursement r) -> {
			System.out.println(r);
		});
		return reimbursementDao.viewPending(name);
	}

	// list of resolved by name
	public List<Reimbursement> getResolved(String name) {
		reimbursementDao.viewPending(name).forEach((Reimbursement r) -> {
			System.out.println(r);
		});
		return reimbursementDao.viewPending(name);
	}

	// change selected employee
	public void changeSelectedEmployee(String name) {
		setSelectedEmployee(employeeDao.viewEmployee(name));
	}

	// 1 accept or deny requests
	public void reviewReimbursements() {
		getAllPending();
		System.out.println("Enter employee id: ");
		int id = sc.nextInt();
		modifyPending(id);
		
	}

	private void modifyPending(int id) {
		System.out.println("1 to accept request, 2 to deny request");
		String i = sc.next();
		switch(i) {
		case "1":
			reimbursementDao.requestAccepted(selectedReimbursement);
			break;
		case"2":
			reimbursementDao.requestDenied(selectedReimbursement);
			break;
		default:
			System.out.println("input not recognized");
			break;
		}
	}

	// 2 get all pending
	public void viewAllPendingReimbursements() {
		System.out.println("Pending Reimbursements");
		getAllPending();
	}

	//3 get all resolved
	public void viewAllResolvedReimbursements() {
		System.out.println("All Resolved Reimbursements");
		getAllResolved();
	}

	//4 view all employees
	public List<Employee> viewAllEmployees() {
		System.out.println("All Employee names");
		return employeeDao.viewEmployees();
	}

	//5 view single employee requests
	public void viewEmployeeRequests() {
		System.out.println("Enter employee name");
		String name = sc.next();
		System.out.println("All Employee Requests");
		reimbursementDao.viewPending(name);
	}

	//6 view single employee
	public void viewEmployee() {
		System.out.println("Enter employee name");
		String emp_username = sc.next();
		System.out.println(" All information of employee");
		employeeDao.viewEmployee(emp_username);
	}
	
	// getters and setters
		public Employee getSelectedEmployee() {
			return selectedEmployee;
		}

		public void setSelectedEmployee(Employee selectedEmployee) {
			this.selectedEmployee = selectedEmployee;
		}

		public Reimbursement getSelectedReimbursement() {
			return selectedReimbursement;
		}

		public void setSelectedReimbursement(Reimbursement selectedReimbursement) {
			this.selectedReimbursement = selectedReimbursement;
		}

}
