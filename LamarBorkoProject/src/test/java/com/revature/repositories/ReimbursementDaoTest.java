package com.revature.repositories;

import org.junit.Test;
//import org.junit.Assert;
import org.postgresql.util.PSQLException;

import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.repositories.ReimbursementDao;
import com.revature.services.ManagerService;
import com.revature.repositories.ReimbursementDaoJDBC;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

public class ReimbursementDaoTest {

	ManagerService managerService = new ManagerService();
	ReimbursementDao reimbursementDao = new ReimbursementDaoJDBC();
	EmployeeDao employeeDao = new EmployeeDaoJDBC();

	@Test
	public void createNewReimbursement() {
		reimbursementDao.createReimbursement(new Reimbursement(20, 3, "test title", 2020, null, null, null, 0));
	}

	@Test
	public void getAllPendingReimbursements() {
		reimbursementDao.viewAllPending();
	}

	@Test
	public void getAllResolvedReimbursements() {
		reimbursementDao.viewAllResolved();
	}

	@Test
	public void getEmployeePendingReimbursements() {
		Employee e = employeeDao.getEmployee("defaultusername", "defaultpassword");
		reimbursementDao.viewPending(e.getId());
	}

	@Test
	public void getEmployeeResolvedReimbursements() {
		Employee e = employeeDao.getEmployee("defaultusername", "defaultpassword");
		reimbursementDao.viewResolved(e.getId());

	}

	

}
