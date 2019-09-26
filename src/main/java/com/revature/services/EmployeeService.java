package com.revature.services;

import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.repositories.EmployeeDao;
import com.revature.repositories.EmployeeDaoJDBC;
import com.revature.repositories.ReimbursementDao;
import com.revature.repositories.ReimbursementDaoJDBC;

public class EmployeeService {
	
	private Employee selectedEmployee = new Employee(0, null, null, null, null, null, null);
	private EmployeeDao employeeDao = new EmployeeDaoJDBC();
	private ReimbursementDao reimbursementDao = new ReimbursementDaoJDBC();
	
	public Employee getSelectedEmployee() {
		return selectedEmployee;
	}

	public void setSelectedEmployee(Employee selectedEmployee) {
		this.selectedEmployee = selectedEmployee;
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
	}

	

}
