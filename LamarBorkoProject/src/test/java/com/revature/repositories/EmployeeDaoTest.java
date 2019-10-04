package com.revature.repositories;

import org.junit.Test;
//import org.junit.Assert;
import org.postgresql.util.PSQLException;

import com.revature.model.Employee;
import com.revature.repositories.EmployeeDao;
import com.revature.services.EmployeeService;
import com.revature.repositories.EmployeeDaoJDBC;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class EmployeeDaoTest {

	EmployeeService employeeService = new EmployeeService();
	EmployeeDao employeeDao = new EmployeeDaoJDBC();

	@Test
	public void createNewEmployee() {
		employeeDao.createEmployee(new Employee(0, null, null, null, null, null, null));
	}

	@Test
	public void logInToAccount() {
		Employee e = employeeDao.getEmployee("defaultusername", "defaultpassword");
		employeeDao.getEmployee(e.getEmp_username(), e.getEmp_password());
	}

	@Test
	public void logInToAccount2() {
		Employee e = new Employee(14, "First", "Last", "Email", "Username", "Password", "Employee");
		employeeDao.getEmployee(e.getEmp_username(), e.getEmp_password());
	}

	@Test
	public void updateEmployee() {
		Employee e = employeeDao.getEmployee("defaultusername", "defaultpassword");
		employeeDao.updateEmployee(e);
	}

}
