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
	private Reimbursement selectedReimbursement = new Reimbursement(0, 0, null, 0, null, null, null);

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

	// list of pending and resolved of selected employee
	public List<Reimbursement> getPending() {
		reimbursementDao.viewAllPending().forEach((Reimbursement r) -> {
			System.out.println(r);
		});
		return reimbursementDao.viewAllPending();
	}

	public List<Reimbursement> getResolved() {
		reimbursementDao.viewAllPending().forEach((Reimbursement r) -> {
			System.out.println(r);
		});
		return reimbursementDao.viewAllPending();
	}

	// list of pending and resolved by name
	public List<Reimbursement> getPending(String name) {
		reimbursementDao.viewPending(name).forEach((Reimbursement r) -> {
			System.out.println(r);
		});
		return reimbursementDao.viewPending(name);
	}

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
		System.out.println("Reimbursements to be reviewed");
		getPending();
		System.out.println("Choose a reimbursement");
		int id = sc.nextInt();
		modifyPending(id);
		
	}

	private void modifyPending(int id) {
		System.out.println("Select an Employee");
		System.out.println("Select a reimbursement");
		
		reimbursementDao.changeStatus(selectedReimbursement);
	}

	// 2 get all pending
	public void viewAllPendingReimbursements() {
		System.out.println("Pending Reimbursements");
		getPending();
	}

	//3 get all resolved
	public void viewAllResolvedReimbursements() {
		System.out.println("All Resolved Reimbursements");
		getResolved();
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
		String name = sc.next();
		System.out.println(" All information of employee");
		employeeDao.viewEmployee(name);
	}

}
