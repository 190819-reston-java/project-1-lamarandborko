package com.revature.repositories;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Reimbursement;
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
    	ResultSet resultSet = null;
		PreparedStatement statement =  null;
		Employee employee = null;
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			statement = conn.prepareStatement(
					"SELECT * FROM project_1.employees WHERE id = ?;");
			statement.setInt(1, id);
			if(statement.execute()) {
				resultSet =  statement.getResultSet();
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
    public Employee viewEmployee(String emp_username) {
    	ResultSet resultSet = null;
		PreparedStatement statement =  null;
		Employee employee = null;
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			statement = conn.prepareStatement(
					"SELECT * FROM project_1.employees WHERE emp_username=?;");
			statement.setString(1, emp_username);
			if(statement.execute()) {
				resultSet =  statement.getResultSet();
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
    @Override
    public boolean createEmployee(Employee e) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        String query = "INSERT INTO project_1.employees VALUES (DEFAULT, ?, ?, ?, ?, ?, 'Employee');";
        
        try {
            conn = ConnectionUtil.getConnection();
            stmt = conn.prepareStatement(query);
            stmt.setString(1, e.getFirst_name());
            stmt.setString(2, e.getLast_name());
            stmt.setString(3, e.getEmail());
            stmt.setString(4, e.getEmp_username());
            stmt.setString(5, e.getEmp_password());
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
}