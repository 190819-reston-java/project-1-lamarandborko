package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.controller.Controller;
import com.revature.model.Employee;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.StreamCloser;

public class EmployeeDaoJDBC implements EmployeeDao {
	@Override
	public Employee getEmployee(String emp_username) {
		ResultSet resultSet = null;
		//PreparedStatements are better than simple ones
		PreparedStatement statement =  null;
		Employee employee = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			statement = conn.prepareStatement(
					"SELECT * FROM project_1.employees WHERE emp_username = ?;");
		
			//fill in the ? with name argument
			statement.setString(1, emp_username);
			
			//try to execute SQL query
			if(statement.execute()) {
				//get the ResultSet
				resultSet =  statement.getResultSet();
				//check for a single row and use it
				if(resultSet.next()) {
					employee = createEmployeeFromRS(resultSet);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			StreamCloser.close(resultSet);
			StreamCloser.close(statement);
		}
		
		return employee;
	}
	
	@Override
	public Employee viewEmployee(int id) {
		Employee employee = null;
		try(Connection conn = ConnectionUtil.getConnection()){
			String query = "SELECT * FROM project_1.employees WHERE id = ?;";
			try(PreparedStatement stmt = conn.prepareStatement(query)){
				stmt.setInt(1, id);
				if(stmt.execute()) {
					try(ResultSet resultSet =stmt.getResultSet()){
						if(resultSet.next()	) {
							employee = createEmployeeFromRS(resultSet);
						}
					}
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}
	
	@Override
	public Employee getEmployee(String emp_username, String emp_password) {
		ResultSet resultSet = null;
		PreparedStatement statement =  null;
		Employee employee = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			statement = conn.prepareStatement(
					"SELECT * FROM project_1.employees WHERE emp_username = ?AND emp_password = ?;");
		
			//fill in the ? with name argument
			statement.setString(1, emp_username);
			statement.setString(2, emp_password);
			//try to execute SQL query
			if(statement.execute()) {
				//get the ResultSet
				resultSet =  statement.getResultSet();
				//check for a single row and use it
				if(resultSet.next()) {
					employee = createEmployeeFromRS(resultSet);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			StreamCloser.close(resultSet);
			StreamCloser.close(statement);
		}
		
		return employee;
	}

	private Employee createEmployeeFromRS(ResultSet resultSet) throws SQLException {
		return new Employee(
				resultSet.getInt("id"),
				resultSet.getString("first_name"),
				resultSet.getString("last_name"),
				resultSet.getString("email"),
				resultSet.getString("emp_username"),
				resultSet.getString("emp_password"),
				resultSet.getString("emp_type"));
	}

	@Override
	public List<Employee> viewEmployees() {
		Statement statement = null;
		ResultSet resultSet = null;
		Connection conn = null;
		// List to return
		List<Employee> employee = new ArrayList<Employee>();
		try {
			// get connection from ConnectionUtil:
			conn = ConnectionUtil.getConnection();

			// create statement from connection
			statement = conn.createStatement();

			// Statements can execute sql queries:
			// ResultSet stores the results of a query
			
			resultSet = statement.executeQuery("SELECT * FROM project_1.employees WHERE id = "+ Controller.currentEmployee.id +";");

			// loop through ResultSet
			while (resultSet.next()) {
				// At each row in the ResultSet, do the following:
				employee.add(createEmployeeFromRS(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// close all open resources to prevent memory leak
			StreamCloser.close(resultSet);
			StreamCloser.close(statement);
			StreamCloser.close(conn);
		}

		return employee;
	}

	@Override
	public boolean createEmployee(Employee e) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String query = "INSERT INTO project_1.employees VALUES (DEFAULT, ?, ?, ?, ?, ?, ?);";
		
		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setString(1, e.getFirst_name());
			stmt.setString(2, e.getLast_name());
			stmt.setString(3, e.getEmail());
			stmt.setString(4, e.getEmp_username());
			stmt.setString(5, e.getEmp_password());
			stmt.setString(6, e.getEmp_type());
			stmt.execute();
		} catch (SQLException sql) {
			sql.printStackTrace();
			return false;
		} finally {
			StreamCloser.close(stmt);
			StreamCloser.close(conn);
		}
		
		return true;
	}
	
	@Override
	public boolean updateEmployee(Employee e) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String query = "UPDATE project_1.employees SET first_name=?, last_name=?, email=?, emp_username=?, emp_password=? WHERE id=?;";
		
		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setString(1, e.getFirst_name());
			stmt.setString(2,  e.getLast_name());
			stmt.setString(3, e.getEmail());
			stmt.setString(4, e.getEmp_username());
			stmt.setString(5, e.getEmp_password());
			stmt.setInt(6, e.getId());
		} catch (SQLException sql) {
			sql.printStackTrace();
			return false;
		}
			finally {
				StreamCloser.close(stmt);
				StreamCloser.close(conn);
		}
		return true;
	}
	
	public Employee getEmployee(int id) {
		Employee employee = null;
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM project_1.employees WHERE id = ?;";
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setLong(1, id);
				if (stmt.execute()) {
					try (ResultSet resultSet = stmt.getResultSet()) {
						if (resultSet.next()) {
							employee = createEmployeeFromRS(resultSet);
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return employee;
	}
    @Override
    public List<Employee> viewAllEmployees() {
    	Statement statement = null;
		ResultSet resultSet = null;
		Connection conn = null;

		List<Employee> employees = new ArrayList<Employee>();

		try {
			conn = ConnectionUtil.getConnection();
			statement = conn.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM project_1.employees;");
			while (resultSet.next()) {
				employees.add(createEmployeeFromRS(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			StreamCloser.close(resultSet);
			StreamCloser.close(statement);
			StreamCloser.close(conn);
		}

		return employees;
    }

}
