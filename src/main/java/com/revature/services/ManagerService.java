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

	private EmployeeDao employeeDao = new EmployeeDaoJDBC();
	private ReimbursementDao reimbursementDao = new ReimbursementDaoJDBC();
	private static Scanner sc = new Scanner(System.in);

	// 1 accept or deny requests
	public void reviewReimbursements() {
		viewAllPendingReimbursements();
		System.out.println("Enter reimbursement id: ");
		int id = sc.nextInt();
		modifyPending(id);
		
	}

	private void modifyPending(int id) {
		System.out.println("1 to accept request, 2 to deny request");
		String i = sc.next();
		switch(i) {
		case "1":
			reimbursementDao.requestAccepted(id);
			break;
		case"2":
			reimbursementDao.requestDenied(id);
			break;
		default:
			System.out.println("input not recognized");
			break;
		}
	}

	// 2 get all pending
	public List<Reimbursement> viewAllPendingReimbursements() {
		reimbursementDao.viewAllPending()
		.forEach((Reimbursement r)->{System.out.println(r);});
		return reimbursementDao.viewAllPending();
	}

	//3 get all resolved
	public List<Reimbursement> viewAllResolvedReimbursements() {
		reimbursementDao.viewAllResolved()
		.forEach((Reimbursement r)->{System.out.println(r);});
		return reimbursementDao.viewAllResolved();
	}

	//4 view all employees
	public List<Employee> viewAllEmployees() {
		employeeDao.viewEmployees()
		.forEach((Employee e)->{System.out.println(e);});
		return employeeDao.viewEmployees();
	}

	//5 view single employee reimbursement requests
	public List<Reimbursement> viewEmployeeRequests() {
		viewAllEmployees();
		System.out.println("Enter employee Id");
		int employee_id = sc.nextInt();
		reimbursementDao.viewPending(employee_id)
		.forEach((Reimbursement r)->{System.out.println(r);});
		 return reimbursementDao.viewPending(employee_id);
	}

	//6 view single employee
	public Employee viewEmployee() {
		System.out.println("Enter employee Id");
		int id = sc.nextInt();
		System.out.println(employeeDao.viewEmployee(id).toString());
		return employeeDao.viewEmployee(id);
	}
	

}
