package com.revature.repositories;

import java.util.List;
import com.revature.model.Employee;


public interface EmployeeDao {
	
	Employee viewEmployee(int id);
	
	Employee viewEmployee(String name);
	
	Employee getUser(String username, String password);
	
	List<Employee> viewEmployees();
	
	boolean createEmployee(Employee e);
	
}
