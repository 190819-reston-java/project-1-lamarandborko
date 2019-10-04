package com.revature.repositories;

import java.util.List;
import com.revature.model.Employee;


public interface EmployeeDao {
	Employee getEmployee(String username);
	
	Employee viewEmployee(int id);
	
	Employee getEmployee(String username, String password);
	
	List<Employee> viewEmployees();
	
	boolean createEmployee(Employee e);
	
	boolean updateEmployee(Employee e);

	List<Employee> viewAllEmployees();

	
}
